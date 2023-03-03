CREATE DATABASE risk_assessment_db;
use risk_assessment_db;


-----------> CUSTOMER DETAILS <----------
		
			CREATE TABLE customer_details_table(
				    RISK_ID INT AUTO_INCREMENT,
				    USER_NAME VARCHAR(255),
				    ORGANISATION_NAME VARCHAR(255),
				    ADDRESS VARCHAR(255),
				    PROJECT_NAME VARCHAR(255),
				    PROJECT_DESC VARCHAR(500),
				    CONTACT_PERSON_NAME VARCHAR(255),
				    CONTACT_NO VARCHAR(255),
				    EMAIL VARCHAR(255),
				    PREPARED_BY VARCHAR(255),
				    VERIFIED_BY VARCHAR(255),
				    ALL_STEPS_COMPLETED VARCHAR(25),
	                STATUS VARCHAR(25),
				    CREATED_BY VARCHAR(255),
				    CREATED_DATE datetime,
			        UPDATED_BY VARCHAR(255),
			        UPDATED_DATE datetime,
			        CONSTRAINT PK_RISK_ID  PRIMARY KEY(RISK_ID)
			        )
			        
			        
-----------> STRUCTURE DETAILS <----------	

		   CREATE TABLE structure_characterisics_table(
				    STRUCTURE_CHARACTERISTIC_ID INT AUTO_INCREMENT,
				    USER_NAME VARCHAR(255),
				    RISK_ID INT,
				    LOCATION VARCHAR(255),
				    PROJECT_NAME VARCHAR(255),
				    OTHER_LOCATION VARCHAR(255),
				    GROUND_FLASH_DENSITY VARCHAR(255),
				    TYPE_OF_BUILDING VARCHAR(255),
				    ST_SCREENING_EFFECTIVENESS VARCHAR(255),
				    IN_SCREENING_EFFECTIVENESS VARCHAR(255),
				    PROTRUSION_LENGHT VARCHAR(255),
				    PROTRUSION_WIDTH VARCHAR(255),
				    PROTRUSION_HEIGHT VARCHAR(255),
				    HEIGHEST_ROOF_PROTRUSION VARCHAR(255),
				    COLL_AREA_STRUCTURE VARCHAR(255),
				    COLL_AREA_STRUC_PROTRUSION VARCHAR(255),
				    COLL_AREA_NEAR_STRUC VARCHAR(255),
				    HEIGHT_NEARBY_STRUCTURE VARCHAR(255),
				    TELEPHONE_SERVICE_LINE VARCHAR(255),
				    ENVIRONMENT VARCHAR(255),
				    NO_OF_DANG_EVENT_ON_STRUCTURE VARCHAR(255),
				    NO_OF_DANG_EVENT_NEAR_STRUCTURE VARCHAR(255),
				    PROTECTION_PART_BUILDING VARCHAR(255),
				    PROTECTION_LENGHT VARCHAR(255),
				    PROTECTION_WIDTH VARCHAR(255),
				    PROTECTION_HEIGHT VARCHAR(255),
				    PRO_COLL_AREA VARCHAR(255),
				    ADJACENT_BUILDING VARCHAR(255),
				    ADJ_LENGTH VARCHAR(255),
				    ADJ_WIDTH VARCHAR(255),
				    ADJ_HEIGHT VARCHAR(255),
				    COLL_AREA_ADJ_STRUC VARCHAR(255),
				    NO_OF_DANG_EVENT_ON_ADJ_STRUC VARCHAR(255),
				    NO_OF_PEOPLE_BUILDING VARCHAR(255),
				    NO_OF_PEOPLE_ZONE VARCHAR(255),
				    DAY_PEOP_PRESENT__BUILDING VARCHAR(255),
				    YEAR_PEOP_PRESENT__BUILDING VARCHAR(255),
			        CREATED_BY VARCHAR(255),
				    CREATED_DATE datetime,
			        UPDATED_BY VARCHAR(255),
			        UPDATED_DATE datetime,
					CONSTRAINT PK_STRUCTURE_CHARACTERISTIC_ID  PRIMARY KEY(STRUCTURE_CHARACTERISTIC_ID)
					);
					
		   CREATE TABLE structure_attributes_table(
				    STRUCTURE_ATTRIBUTES_ID INT AUTO_INCREMENT,
				    STRUCTURE_CHARACTERISTIC_ID INT,
				    ST_TYPE_FLOOR_SURFACE VARCHAR(255),
				    ST_ADDITIONAL_PROTECTION VARCHAR(255),
				    ST_RISK_OF_FIRE VARCHAR(255),
				    ST_FIRE_PROTECTION_MEASURES VARCHAR(255),
				    ST_TYPE_INTERNAL_WIRING VARCHAR(255),
				    TOTAL_NO_OF_LINES VARCHAR(255),
				    NUMBER_POWER_LINES VARCHAR(255),
				    TYPE_POWER_LINES VARCHAR(255),
				    LENGTH_POWER_LINES VARCHAR(255),
				    SHIELD_GROUND_ISOLA VARCHAR(255),
				    COLL_AREA_POWER_LINES VARCHAR(255),
				    COLL_AREA_NEAR_LINES VARCHAR(255),
				    EVENT_NEAR_POWER_LINES VARCHAR(255),
				    EVENT_ON_POWER_LINES VARCHAR(255),
				    NO_OF_TELECOM_LINES VARCHAR(255),
				    TYPE_OF_TELECOM_LINES VARCHAR(255),
				    LENGTH_TELECOM_LINES VARCHAR(255), 
				    SHIELD_GROUND_ISOLA_L1 VARCHAR(255),
				    COLL_AREA_TELECOM_LINES VARCHAR(255),
				    COLL_NEAR_TELECOM_LINES VARCHAR(255),
				    EVENT_NEAR_TELECOM_LINES VARCHAR(255),
				    EVENT_ON_TELECOM_LINES VARCHAR(255),
               
					CONSTRAINT PK_STRUCTURE_ATTRIBUTES_ID PRIMARY KEY(STRUCTURE_ATTRIBUTES_ID),
					CONSTRAINT FK_STRUCTURE_CHARACTERISTIC_ID FOREIGN KEY (STRUCTURE_CHARACTERISTIC_ID) REFERENCES structure_characterisics_table(STRUCTURE_CHARACTERISTIC_ID) ON DELETE CASCADE    
					);
					
					
	    	 CREATE TABLE losses_table(
				    LOSSES_ID INT AUTO_INCREMENT,
				    STRUCTURE_CHARACTERISTIC_ID INT,
				    HL_HAZARD_CLASSIFICATION VARCHAR(255),
				    HL_PHYSICAL_DAMAGE VARCHAR(255),
				    HL_FAILURE_INTERNAL_SYSTEM VARCHAR(255),
				    HL_INJURY_ELECTRIC_SHOCK VARCHAR(255),
				    HL_PHYSICAL_DAMAGE_L1 VARCHAR(255),
				    HL_FAILURE_INTERNAL_SYSTEM_L1 VARCHAR(255),
				    SP_PHYSICAL_DAMAGE VARCHAR(255),
				    SP_FAILURE_INTERNAL_SYSTEM VARCHAR(255),
				    SP_PHYSICAL_DAMAGE_L1 VARCHAR(255),
				    SP_FAILURE_INTERNAL_SYSTEM_L1 VARCHAR(255),
				    CH_PHYSICAL_DAMAGE VARCHAR(255),
				    CH_PHYSICAL_DAMAGE_L1 VARCHAR(255),
				    EL_PHYSICAL_DAMAGE VARCHAR(255),
				    EL_FAILURE_INTERNAL_SYSTEM VARCHAR(255),
				    EL_INJURY_ELECTRIC_SHOCK VARCHAR(255),
				    EL_PHYSICAL_DAMAGE_L1 VARCHAR(255),
				    EL_FAILURE_INTERNAL_SYSTEM_L1 VARCHAR(255),
				    PROTEC_CLASS_LPS VARCHAR(255),
				    PROTEC_CLASS_SPD VARCHAR(255),
               
					CONSTRAINT PK_LOSSES_ID PRIMARY KEY(LOSSES_ID),
					CONSTRAINT FK_LOSSES_STRUCTURE_CHARACTERISTIC_ID FOREIGN KEY (STRUCTURE_CHARACTERISTIC_ID) REFERENCES structure_characterisics_table(STRUCTURE_CHARACTERISTIC_ID) ON DELETE CASCADE    
					);
					
					
			 CREATE TABLE protection_table(
				    PROTECTION_ID INT AUTO_INCREMENT,
				    STRUCTURE_CHARACTERISTIC_ID INT,
				    PRO_PEB VARCHAR(255),
				    PRO_PMS VARCHAR(255),
				    PRO_PM VARCHAR(255),
			        PRO_PA VARCHAR(255),
			        PRO_PC VARCHAR(255),
			        PRO_PU VARCHAR(255),
			        PRO_PV VARCHAR(255),
			        PRO_PW VARCHAR(255),
			        PRO_PZ VARCHAR(255),
			        R_PRO_RA1 VARCHAR(255),
			        R_PRO_RB1 VARCHAR(255),
			        R_PRO_RC1 VARCHAR(255),
			        R_PRO_RM1 VARCHAR(255),
			        R_PRO_RU1 VARCHAR(255),
			        R_PRO_RV1 VARCHAR(255),
			        R_PRO_RW1 VARCHAR(255),
			        R_PRO_RZ1 VARCHAR(255),
			        R_PRO_RB2 VARCHAR(255),
			        CULTURAL_RB VARCHAR(255),
			        CULTURAL_RV VARCHAR(255),
			        CONSTRAINT PK_PROTECTION_ID PRIMARY KEY(PROTECTION_ID),
					CONSTRAINT FK_PROTECTION_STRUCTURE_CHARACTERISTIC_ID FOREIGN KEY (STRUCTURE_CHARACTERISTIC_ID) REFERENCES structure_characterisics_table(STRUCTURE_CHARACTERISTIC_ID) ON DELETE CASCADE    
					);
					
			CREATE TABLE riskprotection_table (
				    RISK_PROTECTION_ID INT AUTO_INCREMENT,
				    STRUCTURE_CHARACTERISTIC_ID INT,
				    R_PRO_RC2 VARCHAR(255),
				    R_PRO_RM2 VARCHAR(255),
				    R_PRO_RV2 VARCHAR(255),
				    R_PRO_RW2 VARCHAR(255),
				    R_PRO_RZ2 VARCHAR(255),
				    ECO_VALUE_RA VARCHAR(255),
				    ECO_VALUE_RB VARCHAR(255),
				    ECO_VALUE_RC VARCHAR(255),
				    ECO_VALUE_RM VARCHAR(255),
				    ECO_VALUE_RU VARCHAR(255),
				    ECO_VALUE_RV VARCHAR(255),
				    ECO_VALUE_RW VARCHAR(255),
				    ECO_VALUE_RZ VARCHAR(255),
				    
					CONSTRAINT PK_RISK_PROTECTION_ID PRIMARY KEY(RISK_PROTECTION_ID),
					CONSTRAINT FK_RISK_PROTECTION_STRUCTURE_CHARACTERISTIC_ID FOREIGN KEY (STRUCTURE_CHARACTERISTIC_ID) REFERENCES structure_characterisics_table(STRUCTURE_CHARACTERISTIC_ID) ON DELETE CASCADE    
					);		 
					
					CREATE TABLE calculated_risk_table (
				    CALCULATED_RISK_ID INT AUTO_INCREMENT,
				    STRUCTURE_CHARACTERISTIC_ID INT,
				    LOSS_HUMAN_LIFE VARCHAR(255),
				    LOSS_PUBLIC_SERVICE VARCHAR(255),
				    LOSS_CULTURAL_HERITAGE VARCHAR(255),
				    ECONOMIC_LOSS VARCHAR(255),
				    R_PRO_R1 VARCHAR(255),
				    R_PRO_R2 VARCHAR(255),
				    R_PRO_R3 VARCHAR(255),
				    R_PRO_R4 VARCHAR(255),
				    R_PRO_RD1 VARCHAR(255),
				    R_PRO_RD2 VARCHAR(255),
				    R_PRO_RD3 VARCHAR(255),
				    R_PRO_RD4 VARCHAR(255),
				    R_PRO_RI1 VARCHAR(255),
				    R_PRO_RI2 VARCHAR(255),
				    R_PRO_RI3 VARCHAR(255),
				    R_PRO_RI4 VARCHAR(255),
				    
					CONSTRAINT PK_CALCULATED_RISK_ID PRIMARY KEY(CALCULATED_RISK_ID),
					CONSTRAINT FK_CALCULATED_STRUCTURE_CHARACTERISTIC_ID FOREIGN KEY (STRUCTURE_CHARACTERISTIC_ID) REFERENCES structure_characterisics_table(STRUCTURE_CHARACTERISTIC_ID) ON DELETE CASCADE    
					);		 
					
					
	-----------> GROUND FLASH dENSITY DETAILS <----------
						
					
					CREATE TABLE ground_flash_density_table(
				    GROUND_DENSITY_ID INT AUTO_INCREMENT,
				    LOCATION VARCHAR(255),
				    GFD_VALUE VARCHAR(255),
			        CONSTRAINT PK_GROUND_DENSITY_ID  PRIMARY KEY(GROUND_DENSITY_ID)
			        );
			        
--			Below data's are fetched from UI

--			        INSERT INTO ground_flash_density_table VALUES (1, 'Others', '0'); 
--			        INSERT INTO ground_flash_density_table VALUES (2, 'Gilgit', '0.7'); 
--			        INSERT INTO ground_flash_density_table VALUES (3, 'skardu', '0.5'); 
--			        INSERT INTO ground_flash_density_table VALUES (4, 'Gulmarg', '5.3'); 
--			        INSERT INTO ground_flash_density_table VALUES (5, 'Srinagar', '5.4'); 
--			        INSERT INTO ground_flash_density_table VALUES (6, 'Dras', '0.3'); 
--			        INSERT INTO ground_flash_density_table VALUES (7, 'kargil', '0.2'); 
--			        INSERT INTO ground_flash_density_table VALUES (8, 'Leh', '0.3'); 
--			        INSERT INTO ground_flash_density_table VALUES (9, 'Jammu', '2.6'); 
--			        INSERT INTO ground_flash_density_table VALUES (10, 'Dharmsala', '1.3');
--			        INSERT INTO ground_flash_density_table VALUES (11, 'Amristar', '4.9'); 
--			        INSERT INTO ground_flash_density_table VALUES (12, 'Pathankot', '0.4'); 
--			        INSERT INTO ground_flash_density_table VALUES (13, 'Mandi', '4.6'); 
--			        INSERT INTO ground_flash_density_table VALUES (14, 'Ludhiana', '1.2');
--			        INSERT INTO ground_flash_density_table VALUES (15, 'Simla', '4.0');
--			        INSERT INTO ground_flash_density_table VALUES (16, 'Patilala', '2.6');
--			        INSERT INTO ground_flash_density_table VALUES (17, 'Ambala', '0.9');
--			        INSERT INTO ground_flash_density_table VALUES (18, 'Hissar', '2.7');
--			        INSERT INTO ground_flash_density_table VALUES (19, 'Delhi', '3.0');
--			        INSERT INTO ground_flash_density_table VALUES (20, 'Bikaner', '1.0');
--			        INSERT INTO ground_flash_density_table VALUES (21, 'Phalodi', '1.4');
--			        INSERT INTO ground_flash_density_table VALUES (22, 'Sikar', '1.7');
--			        INSERT INTO ground_flash_density_table VALUES (23, 'Barmer', '1.2');
--			        INSERT INTO ground_flash_density_table VALUES (24, 'Jodhpur', '2.3');
--			        INSERT INTO ground_flash_density_table VALUES (25, 'Ajmer', '2.6');
--			        INSERT INTO ground_flash_density_table VALUES (26, 'Jaipur', '3.9');
--			        INSERT INTO ground_flash_density_table VALUES (27, 'KanKroli', '3.6');
--			        INSERT INTO ground_flash_density_table VALUES (28, 'Mount Abu', '0.5');
--			        INSERT INTO ground_flash_density_table VALUES (29, 'Udaipur', '3.8');
--			        INSERT INTO ground_flash_density_table VALUES (30, 'Neemuch', '2.8');
--			        INSERT INTO ground_flash_density_table VALUES (31, 'Kota', '2.7');
--			        INSERT INTO ground_flash_density_table VALUES (32, 'Jhalawar', '4.0');
--			        INSERT INTO ground_flash_density_table VALUES (33, 'Mussorie', '6.1');
--			        INSERT INTO ground_flash_density_table VALUES (34, 'Rookee', '7.4');
--			        INSERT INTO ground_flash_density_table VALUES (35, 'Najibabad', '3.6');
--			        INSERT INTO ground_flash_density_table VALUES (36, 'Mukteswar', '5.3');
--			        INSERT INTO ground_flash_density_table VALUES (37, 'Meerut', '0');
--			        INSERT INTO ground_flash_density_table VALUES (38, 'Bareilly', '3.4');
--			        INSERT INTO ground_flash_density_table VALUES (39, 'Aligarh', '3.0');
--			        INSERT INTO ground_flash_density_table VALUES (40, 'Agra', '2.4');
--			        INSERT INTO ground_flash_density_table VALUES (41, 'Mainpuri', '2.3');
--			        INSERT INTO ground_flash_density_table VALUES (42, 'Bahraich', '3.1');
--			        INSERT INTO ground_flash_density_table VALUES (43, 'Gonda', '2.2');
--			        INSERT INTO ground_flash_density_table VALUES (44, 'Lucknow', '1.8');
--			        INSERT INTO ground_flash_density_table VALUES (45, 'Kanpur', '2.6');
--			        INSERT INTO ground_flash_density_table VALUES (46, 'Fatehpur', '2.4');
--			        INSERT INTO ground_flash_density_table VALUES (47, 'Jhansi', '2.0');
--			        INSERT INTO ground_flash_density_table VALUES (48, 'Allahabad', '5.1');
--			        INSERT INTO ground_flash_density_table VALUES (49, 'Varanasi', '5.1');
--			        INSERT INTO ground_flash_density_table VALUES (50, 'Azamgarh', '0.1');
--			        INSERT INTO ground_flash_density_table VALUES (51, 'Gorakhpur', '1.1');
--			        INSERT INTO ground_flash_density_table VALUES (52, 'Katmandu', '7.4');
--			        INSERT INTO ground_flash_density_table VALUES (53, 'Motihari', '3.8');
--			        INSERT INTO ground_flash_density_table VALUES (54, 'Darbhanga', '1.0');
--			        INSERT INTO ground_flash_density_table VALUES (55, 'Patna', '3.3');
--			        INSERT INTO ground_flash_density_table VALUES (56, 'Gaya', '3.8');
--			        INSERT INTO ground_flash_density_table VALUES (57, 'Daltonganj', '7.3');
--			        INSERT INTO ground_flash_density_table VALUES (58, 'Hazaribagh', '7.3');
--			        INSERT INTO ground_flash_density_table VALUES (59, 'Ranchi', '3.4');
--			        INSERT INTO ground_flash_density_table VALUES (60, 'Chaibasa', '7.0');
--			        INSERT INTO ground_flash_density_table VALUES (61, 'Jamshedpur', '6.6');
--			        INSERT INTO ground_flash_density_table VALUES (62, 'Purnea', '5.2');
--			        INSERT INTO ground_flash_density_table VALUES (63, 'Sabour', '7.6');
--			        INSERT INTO ground_flash_density_table VALUES (64, 'Dumka', '6.3');
--			        INSERT INTO ground_flash_density_table VALUES (65, 'Darjeeling', '2.8');
--			        INSERT INTO ground_flash_density_table VALUES (66, 'Jalpaiguri', '6.8');
--			        INSERT INTO ground_flash_density_table VALUES (67, 'Malda', '5.9');
--			        INSERT INTO ground_flash_density_table VALUES (68, 'Asansol', '7.1');
--			        INSERT INTO ground_flash_density_table VALUES (69, 'Burdwan', '3.9');
--			        INSERT INTO ground_flash_density_table VALUES (70, 'Kharagpur', '7.6');
--			        INSERT INTO ground_flash_density_table VALUES (71, 'Calcutta', '7.0');
--			        INSERT INTO ground_flash_density_table VALUES (72, 'Sagar Island', '4.1');
--			        INSERT INTO ground_flash_density_table VALUES (73, 'Dhubri', '0.8');
--			        INSERT INTO ground_flash_density_table VALUES (74, 'Tezpur', '2.7');
--			        INSERT INTO ground_flash_density_table VALUES (75, 'Dibrugarh', '9.8');
--			        INSERT INTO ground_flash_density_table VALUES (76, 'sibsagar', '10.3');
--			        INSERT INTO ground_flash_density_table VALUES (77, 'Shillong', '7.5');
--			        INSERT INTO ground_flash_density_table VALUES (78, 'Cherrapunji', '4.9');
--			        INSERT INTO ground_flash_density_table VALUES (79, 'Silchar', '3.3');
--			        INSERT INTO ground_flash_density_table VALUES (80, 'Kohima', '3.4');
--			        INSERT INTO ground_flash_density_table VALUES (81, 'Imphal', '4.9');
--			        INSERT INTO ground_flash_density_table VALUES (82, 'Deesa', '0.7');
--			        INSERT INTO ground_flash_density_table VALUES (83, 'Dwarka', '0.5');
--			        INSERT INTO ground_flash_density_table VALUES (84, 'Jamnagar', '0.8');
--			        INSERT INTO ground_flash_density_table VALUES (85, 'Rajkot', '1.2');
--			        INSERT INTO ground_flash_density_table VALUES (86, 'Ahmedabad', '1.1');
--			        INSERT INTO ground_flash_density_table VALUES (87, 'Dohad', '1.7');
--			        INSERT INTO ground_flash_density_table VALUES (88, 'Porbandar', '0.3');
--			        INSERT INTO ground_flash_density_table VALUES (89, 'Veraval', '0.3');
--			        INSERT INTO ground_flash_density_table VALUES (90, 'Bhavnagar', '1.1');
--			        INSERT INTO ground_flash_density_table VALUES (91, 'Baroda', '0.8');
--			        INSERT INTO ground_flash_density_table VALUES (92, 'Surat', '0.4');
--			        INSERT INTO ground_flash_density_table VALUES (93, 'Gwalior', '5.3');
--			        INSERT INTO ground_flash_density_table VALUES (94, 'Guna', '3.3');
--			        INSERT INTO ground_flash_density_table VALUES (95, 'Nowgong', '5.9');
--			        INSERT INTO ground_flash_density_table VALUES (96, 'Satna', '4.1');
--			        INSERT INTO ground_flash_density_table VALUES (97, 'Sagar', '3.6');
--			        INSERT INTO ground_flash_density_table VALUES (98, 'Bhopal', '4.4');
--			        INSERT INTO ground_flash_density_table VALUES (99, 'Jabalpur', '5.0');
--			        INSERT INTO ground_flash_density_table VALUES (100, 'Umaria', '3.7');
--					INSERT INTO ground_flash_density_table VALUES (101, 'Ambikapur', '2.9');
--					INSERT INTO ground_flash_density_table VALUES (102, 'Indore', '3.4');
--					INSERT INTO ground_flash_density_table VALUES (103, 'Hoshanagabad', '3.7');
--					INSERT INTO ground_flash_density_table VALUES (104, 'Panchmarhi', '3.0');
--					INSERT INTO ground_flash_density_table VALUES (105, 'Seoni', '5.1');
--					INSERT INTO ground_flash_density_table VALUES (106, 'Pendadah', '5.6');
--					INSERT INTO ground_flash_density_table VALUES (107, 'Raipur', '3.4');
--					INSERT INTO ground_flash_density_table VALUES (108, 'Chhindwara', '2.7');
--					INSERT INTO ground_flash_density_table VALUES (109, 'Kanker', '3.7');
--					INSERT INTO ground_flash_density_table VALUES (110, 'Jagdalpur', '3.5');
--					INSERT INTO ground_flash_density_table VALUES (111, 'Balasore', '8.1');
--					INSERT INTO ground_flash_density_table VALUES (112, 'Chandbali', '7.5');
--					INSERT INTO ground_flash_density_table VALUES (113, 'Angul', '8.1');
--					INSERT INTO ground_flash_density_table VALUES (114, 'Bhubaneswar', '4.6');
--					INSERT INTO ground_flash_density_table VALUES (115, 'Puri', '3.3');
--					INSERT INTO ground_flash_density_table VALUES (116, 'Gopalpur', '3.4');
--					INSERT INTO ground_flash_density_table VALUES (117, 'Jharsuguda', '8.5');
--					INSERT INTO ground_flash_density_table VALUES (118, 'Sambalpur', '6.7');
--					INSERT INTO ground_flash_density_table VALUES (119, 'Titlagarh', '2.4');
--					INSERT INTO ground_flash_density_table VALUES (120, 'Rajgangpur', '0.1');
--					INSERT INTO ground_flash_density_table VALUES (121, 'Dahanu', '0.1');
--					INSERT INTO ground_flash_density_table VALUES (122, 'Nasik', '1.7');
--					INSERT INTO ground_flash_density_table VALUES (123, 'Malegaon', '1.3');
--					INSERT INTO ground_flash_density_table VALUES (124, 'Akola', '2.0');
--					INSERT INTO ground_flash_density_table VALUES (125, 'Amraoti', '3.2');
--					INSERT INTO ground_flash_density_table VALUES (126, 'Nagpur', '4.5');
--					INSERT INTO ground_flash_density_table VALUES (127, 'Gondia', '1.0');
--					INSERT INTO ground_flash_density_table VALUES (128, 'Aurangabad', '3.4');
--					INSERT INTO ground_flash_density_table VALUES (129, 'Mumbai', '1.6');
--					INSERT INTO ground_flash_density_table VALUES (130, 'Alibag', '1.2');
--					INSERT INTO ground_flash_density_table VALUES (131, 'Ahmadnagar', '1.0');
--					INSERT INTO ground_flash_density_table VALUES (132, 'Parbhani', '3.2');
--					INSERT INTO ground_flash_density_table VALUES (133, 'Pune', '2.2');
--					INSERT INTO ground_flash_density_table VALUES (134, 'Mahabaleshwar', '1.4');
--					INSERT INTO ground_flash_density_table VALUES (135, 'Ratnagiri', '0.6');
--					INSERT INTO ground_flash_density_table VALUES (136, 'Sholapur', '2.3');
--					INSERT INTO ground_flash_density_table VALUES (137, 'Miraj', '2.5');
--					INSERT INTO ground_flash_density_table VALUES (138, 'Vengurla', '3.9');
--					INSERT INTO ground_flash_density_table VALUES (139, 'Nizambad', '3.6');
--					INSERT INTO ground_flash_density_table VALUES (140, 'Hnamkonda', '4.3');
--					INSERT INTO ground_flash_density_table VALUES (141, 'Hyderabad', '2.8');
--					INSERT INTO ground_flash_density_table VALUES (142, 'Khammam', '2.6');
--					INSERT INTO ground_flash_density_table VALUES (143, 'Kalingapatam', '2.0');
--					INSERT INTO ground_flash_density_table VALUES (144, 'Vishakapatnam', '4.6');
--					INSERT INTO ground_flash_density_table VALUES (145, 'Rentichintala', '4.2');
--					INSERT INTO ground_flash_density_table VALUES (146, 'Masulipatam', '2.0');
--					INSERT INTO ground_flash_density_table VALUES (147, 'Ongole', '2.5');
--					INSERT INTO ground_flash_density_table VALUES (148, 'Kurnool', '2.9');
--					INSERT INTO ground_flash_density_table VALUES (149, 'Anantpur', '2.2');
--					INSERT INTO ground_flash_density_table VALUES (150, 'Nellore', '1.8');
--					INSERT INTO ground_flash_density_table VALUES (151, 'Bidar', '1.5');
--					INSERT INTO ground_flash_density_table VALUES (152, 'Gulbarga', '3.4');
--					INSERT INTO ground_flash_density_table VALUES (153, 'Bijapur', '0.9');
--					INSERT INTO ground_flash_density_table VALUES (154, 'Belgaum', '3.1');
--					INSERT INTO ground_flash_density_table VALUES (155, 'Raichur', '1.7');
--					INSERT INTO ground_flash_density_table VALUES (156, 'Gadag', '2.1');
--					INSERT INTO ground_flash_density_table VALUES (157, 'Bellary', '2.2');
--					INSERT INTO ground_flash_density_table VALUES (158, 'Karwar', '2.7');
--					INSERT INTO ground_flash_density_table VALUES (159, 'Honavar', '0.5');
--					INSERT INTO ground_flash_density_table VALUES (160, 'Chikalthana', '2.4');
--					INSERT INTO ground_flash_density_table VALUES (161, 'Mangalore', '3.6');
--					INSERT INTO ground_flash_density_table VALUES (162, 'Hassan', '2.6');
--					INSERT INTO ground_flash_density_table VALUES (163, 'Bangalore', '4.6');
--					INSERT INTO ground_flash_density_table VALUES (164, 'Mysore', '4.4');
--					INSERT INTO ground_flash_density_table VALUES (165, 'Kozhikode', '3.9');
--					INSERT INTO ground_flash_density_table VALUES (166, 'Palghat', '3.5');
--					INSERT INTO ground_flash_density_table VALUES (167, 'Cochin', '6.9');
--					INSERT INTO ground_flash_density_table VALUES (168, 'Alleppey', '5.1');
--					INSERT INTO ground_flash_density_table VALUES (169, 'Trivandrum', '6.8');
--					INSERT INTO ground_flash_density_table VALUES (170, 'Vellore', '2.5');
--					INSERT INTO ground_flash_density_table VALUES (171, 'Chennai', '4.7');
--					INSERT INTO ground_flash_density_table VALUES (172, 'Ootacammund', '2.4');
--					INSERT INTO ground_flash_density_table VALUES (173, 'Salem', '6.9');
--					INSERT INTO ground_flash_density_table VALUES (174, 'Cuddalore', '3.7');
--					INSERT INTO ground_flash_density_table VALUES (175, 'Trichchirapalli', '4.1');
--					INSERT INTO ground_flash_density_table VALUES (176, 'Nagapattinam', '1.5');
--					INSERT INTO ground_flash_density_table VALUES (177, 'Kodaikanal', '8.2');
--					INSERT INTO ground_flash_density_table VALUES (178, 'Madurai', '3.9');
--					INSERT INTO ground_flash_density_table VALUES (179, 'Pamban', '0.5');
--					INSERT INTO ground_flash_density_table VALUES (180, 'Tuticorin', '1.4');
--					INSERT INTO ground_flash_density_table VALUES (181, 'Cape comorin', '6.8');
--					INSERT INTO ground_flash_density_table VALUES (182, 'Port Blair', '6.2');
--					INSERT INTO ground_flash_density_table VALUES (183, 'Car Nicobar 1', '1.8');
--					INSERT INTO ground_flash_density_table VALUES (184, 'Minicoy 1', '2.0');
					
					
					
				