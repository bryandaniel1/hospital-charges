/***************************************************************************
* Create the database named hospital_outpatient, its tables, and a user
*****************************************************************************/

DROP DATABASE IF EXISTS hospital_outpatient;
CREATE DATABASE hospital_outpatient;
USE hospital_outpatient;

/*Tables*/

CREATE TABLE State (
		state VARCHAR(50) NOT NULL, 
		CONSTRAINT PK_state PRIMARY KEY(state)
);

CREATE TABLE  Region (
		provider_city VARCHAR(50) NOT NULL, 
		provider_state VARCHAR(50) NOT NULL, 
		provider_hrr VARCHAR(50) NOT NULL, 
		FOREIGN KEY (provider_state) 
		REFERENCES State (state), 
		CONSTRAINT PK_Region_provider_city_provider_state PRIMARY KEY(provider_city, provider_state)
);

CREATE TABLE Provider (
		provider_id INT NOT NULL, 
		provider_name VARCHAR(50) NOT NULL, 
		provider_street VARCHAR(50) NOT NULL, 
		provider_city VARCHAR(50) NOT NULL, 
		provider_state VARCHAR(50) NOT NULL, 
		provider_zip VARCHAR(50) NOT NULL, 
		FOREIGN KEY (provider_city) 
		REFERENCES Region (provider_city), 
		FOREIGN KEY (provider_state) 
		REFERENCES Region (provider_state), 
		CONSTRAINT PK_provider_id PRIMARY KEY(provider_id)
);

CREATE TABLE APC (
		apc_id INT NOT NULL, 
		apc_definition VARCHAR(200) NOT NULL, 
		PRIMARY KEY (apc_id)
);

CREATE TABLE Charge (
		apc_id INT NOT NULL, 
		provider_id INT NOT NULL, 
		avg_charges DECIMAL(8,2) NOT NULL, 
		avg_payments DECIMAL(8,2) NOT NULL, 
		FOREIGN KEY (apc_id) 
		REFERENCES APC (apc_id), 
		FOREIGN KEY (provider_id) 
		REFERENCES Provider (provider_id), 
		CONSTRAINT PK_Charge_apc_id_provider_id PRIMARY KEY(apc_id, provider_id)
);

/*Procedures*/

--getStates
DELIMITER //
CREATE PROCEDURE getStates(IN apc_id_in VARCHAR(50), OUT proc_success TINYINT(1))
BEGIN
	SELECT DISTINCT p.provider_state AS 'state' 
	FROM Provider p, Charge c 
	WHERE p.provider_id = c.provider_id 
	AND c.apc_id = apc_id_in
	ORDER BY p.provider_state;

SET proc_success = 1;

END ; //
DELIMITER ;

--getCities
DELIMITER //
CREATE PROCEDURE getCities(IN state_in VARCHAR(50), OUT proc_success TINYINT(1))
BEGIN
	SELECT r.provider_city AS 'city' 
	FROM Region r 
	WHERE r.provider_state = state_in
	ORDER BY r.provider_city;

SET proc_success = 1;

END ; //
DELIMITER ;

--getCitiesToCompare
DELIMITER //
CREATE PROCEDURE getCitiesToCompare(IN apc_id_in INT, IN state_in VARCHAR(50), 
		OUT proc_success TINYINT(1))
BEGIN
	SELECT r.provider_city AS 'city' 
	FROM Region r, Charge c, Provider p 
	WHERE r.provider_state = state_in 
	AND c.apc_id = apc_id_in 
	AND c.provider_id = p.provider_id 
	AND p.provider_state = r.provider_state 
	AND p.provider_city = r.provider_city 
	ORDER BY r.provider_city;

SET proc_success = 1;

END ; //
DELIMITER ;

--getProviders
DELIMITER //
CREATE PROCEDURE getProviders(IN apc_id_in INT, IN city_in VARCHAR(50), 
		IN state_in VARCHAR(50), OUT proc_success TINYINT(1))
BEGIN

	SELECT DISTINCT p.provider_id AS 'provider id', p.provider_name AS 'provider name', 
		p.provider_street AS 'provider street', p.provider_city AS 'provider city', 
		p.provider_state AS 'provider state', p.provider_zip AS 'provider zip'   
		FROM Provider p, Charge c 
		WHERE p.provider_state = state_in 
		AND p.provider_city = city_in 
		AND p.provider_id = c.provider_id 
		AND c.apc_id = apc_id_in
		ORDER BY p.provider_name;

SET proc_success = 1;

END ; //
DELIMITER ;

--getAPCs
DELIMITER //
CREATE PROCEDURE getAPCs(OUT proc_success TINYINT(1))
BEGIN

	SELECT DISTINCT a.apc_id AS 'apc id', a.apc_definition AS 'apc definition'
		FROM APC a 
		ORDER BY a.apc_definition;

SET proc_success = 1;

END ; //
DELIMITER ;

--getCharges
DELIMITER //
CREATE PROCEDURE getCharges(IN apc_id_in INT, IN provider_id_in INT, 
		OUT proc_success TINYINT(1))
BEGIN
	DECLARE row_num INT DEFAULT 0;
	DECLARE total_num INT DEFAULT 0;
	
	SELECT c.avg_charges AS 'avg charges', c.avg_payments AS 'avg payments'
		FROM Charge c 
		WHERE c.apc_id = apc_id_in 
		AND c.provider_id = provider_id_in;

	DROP TEMPORARY TABLE IF EXISTS Rank;

	CREATE TEMPORARY TABLE Rank (rank_id INT, provider_id INT, 
		avg_charges DECIMAL(8, 2), 
		PRIMARY KEY (rank_id));

	INSERT INTO Rank SELECT @rank:=@rank+1 AS rank, c.provider_id, c.avg_charges 
		FROM APC a, Charge c, (SELECT @rank := 0) r 
		WHERE a.apc_id = c.apc_id 
		AND c.apc_id = apc_id_in 
		ORDER BY c.avg_charges;

	SELECT r.rank_id INTO row_num 
		FROM Rank r 
		WHERE r.provider_id = provider_id_in;

	SELECT COUNT(*) INTO total_num 
		FROM Rank;

	SELECT (100*((row_num-0.5)/total_num)) AS 'avg charges percentile';

	DROP TEMPORARY TABLE IF EXISTS Rank;

	CREATE TEMPORARY TABLE Rank (rank_id INT, provider_id INT, 
		avg_payments DECIMAL(8, 2), 
		PRIMARY KEY (rank_id));

	INSERT INTO Rank SELECT @rank:=@rank+1 AS rank, c.provider_id, c.avg_payments 
		FROM APC a, Charge c, (SELECT @rank := 0) r 
		WHERE a.apc_id = c.apc_id 
		AND c.apc_id = apc_id_in 
		ORDER BY c.avg_payments;

	SELECT r.rank_id INTO row_num 
		FROM Rank r 
		WHERE r.provider_id = provider_id_in;

	SELECT COUNT(*) INTO total_num 
		FROM Rank;

	SELECT (100*((row_num-0.5)/total_num)) AS 'avg payments percentile';

	DROP TEMPORARY TABLE Rank;

SET proc_success = 1;

END ; //
DELIMITER ;

--getRegionalAPCs
DELIMITER //
CREATE PROCEDURE getRegionalAPCs(IN city_in VARCHAR(50), IN state_in VARCHAR(50), 
		OUT proc_success TINYINT(1))
BEGIN

	DECLARE var VARCHAR(50) DEFAULT NULL;

	SELECT r.provider_hrr INTO var 
	FROM Region r 
	WHERE r.provider_state = state_in 
	AND r.provider_city = city_in;

	DROP TEMPORARY TABLE IF EXISTS Location;

	CREATE TEMPORARY TABLE Location(
		temp_city VARCHAR(50) NOT NULL, 
		temp_state VARCHAR(50) NOT NULL, 
		PRIMARY KEY (temp_city, temp_state)
	);

	INSERT INTO Location SELECT r.provider_city, r.provider_state 
		FROM Region r 
		WHERE r.provider_hrr = var;

	DROP TEMPORARY TABLE IF EXISTS RegionalProvider;

	CREATE TEMPORARY TABLE RegionalProvider(
		provider_id INT NOT NULL, 
		PRIMARY KEY (provider_id)
	);

	INSERT INTO RegionalProvider SELECT DISTINCT p.provider_id 
		FROM Provider p, Location l 
		WHERE p.provider_state = l.temp_state 
		AND p.provider_city = l.temp_city;

	SELECT DISTINCT a.apc_id AS 'apc id', a.apc_definition AS 'apc definition'
		FROM APC a, RegionalProvider rp, Charge c 
		WHERE a.apc_id = c.apc_id 
		AND c.provider_id = rp.provider_id
		ORDER BY a.apc_definition;

	DROP TEMPORARY TABLE Location;
	DROP TEMPORARY TABLE RegionalProvider;

SET proc_success = 1;

END ; //
DELIMITER ;

--getRegionalCharges
DELIMITER //
CREATE PROCEDURE getRegionalCharges(IN apc_id_in INT, IN city_in VARCHAR(50), 
		IN state_in VARCHAR(50), OUT proc_success TINYINT(1))
BEGIN

	DECLARE var VARCHAR(50) DEFAULT NULL;

	SELECT r.provider_hrr INTO var 
	FROM Region r 
	WHERE r.provider_state = state_in 
	AND r.provider_city = city_in;

	DROP TEMPORARY TABLE IF EXISTS Location;

	CREATE TEMPORARY TABLE Location(
		temp_city VARCHAR(50) NOT NULL, 
		temp_state VARCHAR(50) NOT NULL, 
		PRIMARY KEY (temp_city, temp_state)
	);

	INSERT INTO Location SELECT r.provider_city, r.provider_state 
		FROM Region r 
		WHERE r.provider_hrr = var;

	DROP TEMPORARY TABLE IF EXISTS RegionalProvider;

	CREATE TEMPORARY TABLE RegionalProvider(
		provider_id INT NOT NULL, 
		provider_name VARCHAR(50) NOT NULL, 
		provider_street VARCHAR(50) NOT NULL, 
		provider_city VARCHAR(50) NOT NULL, 
		provider_state VARCHAR(50) NOT NULL, 
		provider_zip VARCHAR(50) NOT NULL, 
		PRIMARY KEY (provider_id)
	);

	INSERT INTO RegionalProvider SELECT DISTINCT p.provider_id, 
		p.provider_name, p.provider_street, p.provider_city, 
		p.provider_state, p.provider_zip 
		FROM Provider p, Location l 
		WHERE p.provider_state = l.temp_state 
		AND p.provider_city = l.temp_city;

	SELECT rp.provider_id AS 'provider id', rp.provider_name AS 'provider name', 
		rp.provider_street AS 'provider street', rp.provider_city AS 'provider city', 
		rp.provider_state AS 'provider state', rp.provider_zip AS 'provider zip', 
		a.apc_id AS 'apc id', a.apc_definition AS 'apc definition', 
		c.avg_charges AS 'avg charges', c.avg_payments AS 'avg payments'
		FROM Charge c, RegionalProvider rp, APC a 
		WHERE c.provider_id = rp.provider_id
		AND c.apc_id = a.apc_id 
		AND c.apc_id = apc_id_in
		ORDER BY rp.provider_name;

	DROP TEMPORARY TABLE Location;
	DROP TEMPORARY TABLE RegionalProvider;

SET proc_success = 1;

END ; //
DELIMITER ;

--getAPCStats
DELIMITER //
CREATE PROCEDURE getAPCStats(IN apc_id_in INT, OUT proc_success TINYINT(1))
BEGIN
	DECLARE divisor INT DEFAULT 5;
	DECLARE maxCharge DECIMAL(8,2);
	DECLARE minCharge DECIMAL(8,2);
	DECLARE avgCharge DECIMAL(8,2);
	DECLARE avgChargeRange DECIMAL(8,2);
	DECLARE groupSize DECIMAL(8,2);
	DECLARE groupOneCeiling DECIMAL(8,2);
	DECLARE groupTwoCeiling DECIMAL(8,2);
	DECLARE groupThreeCeiling DECIMAL(8,2);
	DECLARE groupFourCeiling DECIMAL(8,2);
	DECLARE medianCharge DECIMAL(8,2);
	DECLARE stdDeviation DECIMAL(8,2);
	DECLARE groupOneCount INT DEFAULT 0;
	DECLARE groupTwoCount INT DEFAULT 0;
	DECLARE groupThreeCount INT DEFAULT 0;
	DECLARE groupFourCount INT DEFAULT 0;
	DECLARE groupFiveCount INT DEFAULT 0;

	SELECT MAX(c.avg_charges) INTO maxCharge
		FROM Charge c 
		WHERE c.apc_id = apc_id_in;

	SELECT MIN(c.avg_charges) INTO minCharge
		FROM Charge c 
		WHERE c.apc_id = apc_id_in;

	SELECT AVG(c.avg_charges) INTO avgCharge
		FROM Charge c 
		WHERE c.apc_id = apc_id_in;

	SELECT AVG(FirstTable.avg_charges) INTO medianCharge 
		FROM 
		(SELECT @rownum:=@rownum+1 AS `row_number`, c.avg_charges 
		FROM Charge c, (SELECT @rownum:=0) r 
		WHERE c.apc_id = apc_id_in 
		ORDER BY c.avg_charges) AS FirstTable, 
		(SELECT COUNT(*) AS total_rows 
		FROM Charge c 
		WHERE c.apc_id = apc_id_in) AS SecondTable 
		WHERE FirstTable.ROW_NUMBER IN (FLOOR((total_rows+1)/2), FLOOR((total_rows+2)/2));

	SELECT maxCharge - minCharge INTO avgChargeRange;

	SELECT (avgChargeRange/divisor) INTO groupSize;

	SELECT STD(c.avg_charges) INTO stdDeviation
		FROM Charge c 
		WHERE c.apc_id = apc_id_in;

			
	SELECT avgCharge AS 'avg charge', medianCharge AS 'median', 
		stdDeviation AS 'std deviation', avgChargeRange AS 'range', 
		minCharge AS 'min charge', maxCharge AS 'max charge';

	SELECT c.avg_charges AS 'avg charges' 
		FROM Charge c 
		WHERE c.apc_id = apc_id_in;

	SELECT p.provider_id AS 'max provider id', p.provider_name AS 'provider name', 
		p.provider_street AS 'provider street', p.provider_city AS 'provider city', 
		p.provider_state AS 'provider state', p.provider_zip AS 'provider zip'  
		FROM Provider p, Charge c 
		WHERE c.apc_id = apc_id_in  
		AND c.provider_id = p.provider_id 
		AND c.avg_charges = maxCharge; 

	SELECT p.provider_id AS 'min provider id', p.provider_name AS 'provider name', 
		p.provider_street AS 'provider street', p.provider_city AS 'provider city', 
		p.provider_state AS 'provider state', p.provider_zip AS 'provider zip'  
		FROM Provider p, Charge c 
		WHERE c.apc_id = apc_id_in 
		AND c.provider_id = p.provider_id 
		AND c.avg_charges = minCharge;


SET proc_success = 1;

END ; //
DELIMITER ;

-- Create hospital_outuser and grant privileges

DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
	BEGIN
	DECLARE userCount BIGINT DEFAULT 0 ;
	SELECT COUNT(*) INTO userCount FROM mysql.user
		WHERE User = 'hospital_outuser' and  Host = 'localhost';

	IF userCount > 0 THEN
        DROP USER hospital_outuser@localhost;
	END IF;
	END ; //
DELIMITER ;
CALL drop_user_if_exists() ;
CREATE USER hospital_outuser@localhost IDENTIFIED BY 'topsecretpassword';
GRANT EXECUTE, SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON hospital_outpatient.*
TO hospital_outuser@localhost;