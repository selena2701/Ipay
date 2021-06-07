CREATE DATABASE DBC_EBAY;
GO
USE DBC_EBAY

-----CREATE TABLE-----

CREATE TABLE USER_ACCOUNT
(
	Username VARCHAR(40) PRIMARY KEY ,
	Password_Login VARCHAR(40),
	Role BIT
)


CREATE TABLE E_CUSTOMER
(
	CUS_ID CHAR(20) PRIMARY KEY ,
	FullnameCUS VARCHAR(40),
	Username VARCHAR(40) FOREIGN KEY REFERENCES USER_ACCOUNT(Username),
	PhoneCUS VARCHAR(10),
	AddressCUS VARCHAR(50),
	NationalID VARCHAR(20),
	Gender VARCHAR(5),
	Birthday SMALLDATETIME,
	Region VARCHAR(20),
	DateRegister DATETIME DEFAULT GETDATE(),
)

CREATE TABLE E_ADMIN
(
	AD_ID CHAR(4) PRIMARY KEY,
	NameAD VARCHAR(40),
	Username VARCHAR(40) FOREIGN KEY REFERENCES USER_ACCOUNT(Username),
	PromotedBy VARCHAR(40),
	DatePromote SMALLDATETIME,
)

CREATE TABLE E_CREDITCARD
(
	CC_ID CHAR(4) PRIMARY KEY,
	CardHolderName VARCHAR(40),
	CardNumber VARCHAR(20),
	AccountNumber VARCHAR(20),
	BankName VARCHAR(20),
	Status VARCHAR(20),
	CUS_ID CHAR(20) REFERENCES E_CUSTOMER(CUS_ID),
)


CREATE TABLE E_ELECTRICITY_TYPE
(
	Electricity_Type CHAR(4) PRIMARY KEY,
	TypeName VARCHAR(20),
)

CREATE TABLE E_ELECTRICITY_BILL
(
	ELEC_BILL_ID CHAR(20) PRIMARY KEY,
	PreviousValue INT,
	CurrentValue INT,
	ConsumeValue INT,
	VAT VARCHAR(5),
	Total MONEY,
	Electricity_Type CHAR(4) REFERENCES E_ELECTRICITY_TYPE(Electricity_Type),
	FromDate SMALLDATETIME,
	ToDate SMALLDATETIME,
	DatePaid SMALLDATETIME,
	StatusBill VARCHAR(6),
	CUS_ID CHAR(20) REFERENCES E_CUSTOMER(CUS_ID)
)


CREATE TABLE E_PROVIDER
(
	PRO_ID CHAR(20) PRIMARY KEY,
	NamePRO VARCHAR(40),
	PhonePRO VARCHAR(10),
	AddressPRO VARCHAR(50), 
	DateJoined SMALLDATETIME,
	PromotedBy CHAR(4) REFERENCES E_ADMIN(AD_ID)
)

CREATE TABLE E_NOTIFICATION
(
	NO_ID CHAR(10) PRIMARY KEY,
	DateCreated SMALLDATETIME,
	DateSent SMALLDATETIME,
	EditedBy VARCHAR(500),
	Detail NVARCHAR(4000),
)

CREATE TABLE E_PAYMENT_SCHEDULE
(
	PS_ID CHAR(4) PRIMARY KEY,
	CUS_ID CHAR(20) REFERENCES E_CUSTOMER(CUS_ID),
	DateSet SMALLDATETIME,
	TimeSet TIMESTAMP,
)

SELECT eb.ELEC_BILL_ID AS "INVOICE ID", cus.FullnameCUS as "PAID BY", EB.Total, eb.DatePaid 
FROM dbo.E_ELECTRICITY_BILL eb, dbo.E_CUSTOMER cus
WHERE eb.CUS_ID=cus.CUS_ID



---INSERT DATABASE---
SET DATEFORMAT DMY 

---USER_ACCOUNT---
--Admin account--
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('user01','7c4a8d09ca3762af61e59520943dc26494f8941b',1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('user02','7c4a8d09ca3762af61e59520943dc26494f8941b',1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('user03','7c4a8d09ca3762af61e59520943dc26494f8941b',1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('user04','7c4a8d09ca3762af61e59520943dc26494f8941b',1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('user05','7c4a8d09ca3762af61e59520943dc26494f8941b',1)
--Customer account-- USERNAME: test  PASSWORD: 123456
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('test','7c4a8d09ca3762af61e59520943dc26494f8941b',0) 

INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('sonnt','ad399e53680cf392645d964535bfb0689dd8a6f3',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('trongnt','f004ec964ecac685ed431824018847ea4c14f32a',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('hongnt','42a7c2725ea9519e85ec5351c3939ae9c96dfe8c',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('sonnm','5c7bd3120114000b4fe531eb9ebbcd10afe1cd68',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('hungtq','7c4a8d09ca3762af61e59520943dc26494f8941b',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('nganvtk','046b93f2e6db9fc539eb2809f51bbe64254e78c7',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('thuongvtn','96e037f0f969558fbb1dfdcac1a8b19162347f02',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('yennnh','4ab6daeafb1c2e3b88e3c026dfe2f0485375fd0d',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('vytdt','889da08cc2d419efaeb23e62317407d6341e5774',0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,Role) VALUES ('nguyentdt','4d097a02ad9d2b88646613cb8a7e5893025f00a6',0)


---E_CUSTOMER---
--Data Customer to Test--
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PN13000064176','TestCustomer','test','0938223920','321 DUONG QUANG HAM','VN18121978','Nu','18/12/1978','Mien Nam')
--
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PE13000064176','Ngo Thi Son','sonnt','0938223920','321 DUONG QUANG HAM','VN18121978','Nu','18/12/1978','Mien Nam')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PC05000002806','Nguyen Thi Trong','trongnt','0768462295','THON 7B DIEN NAM DONG','VN19720302','Nu','03/02/1972','Mien Trung')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PD14000076344','Nguyen Thi Hong','hongnt','0366866700','28 CHUA LANG','VN19881202','Nu','18/02/1988','Mien Bac')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PE13000006345','Ngo Minh Son','sonnm','0902542629','319 DUONG QUANG HAM','VN60606060','Nam','01/03/1961','Mien Nam')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PB03000064176','Tran Quang Hung','hungtq','0909955517','THI TRAN NAM BAN','VN90095478','Nam','08/08/1990','Mien Nam')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PC12000083443','Vo Thi Kim Ngan','nganvtk','0706202009','12 NGUYEN CONG TRU','VN20200128','Nu','28/04/2001','Mien Trung')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PC07000282374','Vo Ngoc Hoai Thuong','thuongvtn','0906300435','KP 4 BONG SON','VN16042001','Nu','07/06/2001','Mien Trung')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PE14000006563','Nguyen Ngoc Hai Yen','yennnh','0777717427','38 THONG NHAT','VN18090819','Nu','19/08/2001','Mien Nam')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PE13000008754','Tran Do Tuong Vy','vytdt','0382601277','426 PHAM VAN CHIEU','VN11110404','Nu','04/11/2001','Mien Nam')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('PB04000657657','Thach Diep Thao Nguyen','nguyentdt','0937004484','KHU TRUNG TAM HANH CHINH DI AN','VN21282828','Nu','28/08/2001','Mien Nam')


---E_ADMIN---
INSERT INTO E_ADMIN(AD_ID,NameAD,Username,PromotedBy,DatePromote) VALUES ('AD01','Le Thi Hong Cuc','user01','','12/12/2029')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD02','Vu Thuy Duong','user02','user01','02/07/2019')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD03','Le Hoang Thinh','user03','user01','06/02/2020')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD04','Phan Thai Tam','user04','user02','09/10/2020')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD05','Le Huynh Lan Ha','user05','user01','07/11/2020')


---E_CREDITCARD---
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC05','TEST CUSTOMER','9704162412945627','10131467','ACB','Default','PN13000064176')

INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC06','Ngo Thi Son','9704162412945627','10131467','ACB','Default','PE13000064176')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC07','Nguyen Thi Trong','4283100123456789','1014778232','Vietcombank','Default','PC05000002806')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC08','Nguyen Thi Hong','4283100001203833','1013270578','Vietcombank','Default','PD14000076344')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC09','Ngo Minh Son','9704180928060512','31410003879783','BIDV','Default','PE13000006345')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC10','Tran Quang Hung','9704051460008688','6400205502067','Agribank','Default','PB03000064176')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC11','Vo Thi Kim Ngan','9704050900708986','5013205074443','Agribank','Default','PC12000083443')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC12','Vo Thi Hoai Thuong','9704180023052806','42610000631999','BIDV','Default','PC07000282374')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC13','Nguyen Ngoc Hai Yen','9704368603868954010','011003697871','Vietcombank','Default','PE14000006563')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC14','Tran Do Tuong Vy','9704991700220295','1700220295509','Agribank','Default','PE13000008754')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,Status,CUS_ID) VALUES ('CC15','Thach Diep Thao Nguyen','4220751256789010','2019002019','Vietcombank','Default','PB04000657657')

--- E_ELECTRICITY_TYPE---
INSERT INTO E_ELECTRICITY_TYPE(Electricity_Type,TypeName) VALUES ('MA01','Dien sinh hoat')
INSERT INTO E_ELECTRICITY_TYPE(Electricity_Type,TypeName) VALUES ('MA02','Dien kinh doanh')
INSERT INTO E_ELECTRICITY_TYPE(Electricity_Type,TypeName) VALUES ('MA03','Dien cong cong')

---E_ELECTRICITY_BILL---
--Data Customer to Test--
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('10000','100','207','107','0.1','201813','MA01','01/11/2019','30/11/2019','05/12/2019','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100001','207','637','430','0.1','1086509','MA01','01/12/2019','31/12/2019','03/01/2020','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100002','637','876','239','0.1','512620','MA01','01/01/2020','31/01/2020','14/02/2020','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100003','876','923','47','0.1','86753','MA01','01/02/2020','29/02/2020','08/03/2020','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100004','923','1100','177','0.1','356891','MA01','01/03/2020','31/03/2020','01/04/2020','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100005','1100','1189','89','0.1','166555','MA01','01/04/2020','30/04/2020','07/05/2020','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100006','1189','1354','165','0.1','330306','MA01','01/05/2020','31/05/2020','28/06/2020','PAID','PN13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100007','1354','1523','169','0.1','339167','MA01','01/06/2020','30/06/2020','','UNPAID','PN13000064176')


---Ngo Thi Son---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100020','100','207','107','0.1','201813','MA01','01/11/2019','30/11/2019','05/12/2019','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100030','207','637','430','0.1','1086509','MA01','01/12/2019','31/12/2019','03/01/2020','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100040','637','876','239','0.1','512620','MA01','01/01/2020','31/01/2020','14/02/2020','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100050','876','923','47','0.1','86753','MA01','01/02/2020','29/02/2020','08/03/2020','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100060','923','1100','177','0.1','356891','MA01','01/03/2020','31/03/2020','01/04/2020','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100070','1100','1189','89','0.1','166555','MA01','01/04/2020','30/04/2020','07/05/2020','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100080','1189','1354','165','0.1','330306','MA01','01/05/2020','31/05/2020','28/06/2020','PAID','PE13000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100090','1354','1523','169','0.1','339167','MA01','01/06/2020','30/06/2020','','UNPAID','PE13000064176')


---Nguyen Thi Trong---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100021','100','207','107','0.1','201813','MA01','01/11/2019','30/11/2019','12/12/2019','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100031','207','387','180','0.1','363537','MA01','01/12/2019','31/12/2019','01/01/2020','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100041','387','455','68','0.1','126500','MA01','01/01/2020','31/01/2020','12/02/2020','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100051','455','652','197','0.1','401199','MA01','01/02/2020','29/02/2020','07/03/2020','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100061','652','892','240','0.1','515409','MA01','01/03/2020','31/03/2020','05/04/2020','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100071','892','961','69','0.1','128407','MA01','01/04/2020','30/04/2020','02/05/2020','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100081','961','1151','190','0.1','385691','MA01','01/05/2020','31/05/2020','05/06/2020','PAID','PC05000002806')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100091','1151','1274','123','0.1','237259','MA01','01/06/2020','30/06/2020','','UNPAID','PC05000002806')

---Nguyen Thi Hong---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100022','100','286','186','0.1','545464','MA02','01/11/2019','30/11/2019','01/12/2019','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100032','286','487','201','0.1','589453','MA02','01/12/2019','31/12/2019','06/01/2020','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100042','487','755','268','0.1','705937','MA02','01/01/2020','31/01/2020','01/02/2020','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100052','755','952','197','0.1','577722','MA02','01/02/2020','29/02/2020','04/03/2020','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100062','952','1192','240','0.1','703824','MA02','01/03/2020','31/03/2020','03/04/2020','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100072','1192','1361','168','0.1','492677','MA02','01/04/2020','30/04/2020','22/05/2020','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100082','1361','1551','190','0.1','557194','MA02','01/05/2020','31/05/2020','14/06/2020','PAID','PD14000076344')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100092','1551','1874','323','0.1','947230','MA02','01/06/2020','30/06/2020','','UNPAID','PD14000076344')

---Ngo Minh Son---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100023','100','286','186','0.1','389149','MA03','01/11/2019','30/11/2019','06/12/2019','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100033','286','487','201','0.1','420532','MA03','01/12/2019','31/12/2019','06/01/2020','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100043','487','755','268','0.1','560710','MA03','01/01/2020','31/01/2020','05/02/2020','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100053','755','952','197','0.1','412163','MA03','01/02/2020','29/02/2020','22/03/2020','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100063','952','1192','240','0.1','502128','MA03','01/03/2020','31/03/2020','13/04/2020','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100073','1192','1361','168','0.1','351490','MA03','01/04/2020','30/04/2020','07/05/2020','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100083','1361','1551','190','0.1','397518','MA03','01/05/2020','31/05/2020','11/06/2020','PAID','PE13000006345')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100093','1551','1874','323','0.1','675781','MA03','01/06/2020','30/06/2020','','UNPAID','PE13000006345')

---Tran Quang Hung---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100024','100','286','186','0.1','389149','MA03','01/11/2019','30/11/2019','05/12/2019','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100034','286','434','148','0.1','309646','MA03','01/12/2019','31/12/2019','02/01/2020','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100044','434','755','321','0.1','671596','MA03','01/01/2020','31/01/2020','05/02/2020','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100054','755','852','97','0.1','202943','MA03','01/02/2020','29/02/2020','12/03/2020','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100064','852','1392','540','0.1','1129788','MA03','01/03/2020','31/03/2020','10/04/2020','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100074','1392','1561','169','0.1','353582','MA03','01/04/2020','30/04/2020','17/05/2020','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100084','1561','1733','172','0.1','359858','MA03','01/05/2020','31/05/2020','01/06/2020','PAID','PB03000064176')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100094','1733','1974','241','0.1','504220','MA03','01/06/2020','30/06/2020','','UNPAID','PB03000064176')

---Vo Thi Kim Ngan---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100025','100','207','107','0.1','201813','MA01','01/11/2019','30/11/2019','05/12/2019','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100035','207','337','130','0.1','252767','MA01','01/12/2019','31/12/2019','13/01/2020','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100045','337','476','139','0.1','272705','MA01','01/01/2020','31/01/2020','04/02/2020','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100055','476','623','147','0.1','290429','MA01','01/02/2020','29/02/2020','05/03/2020','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100065','623','800','177','0.1','356891','MA01','01/03/2020','31/03/2020','09/04/2020','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100075','800','989','189','0.1','383475','MA01','01/04/2020','30/04/2020','12/05/2020','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100085','989','1154','165','0.1','330306','MA01','01/05/2020','31/05/2020','08/06/2020','PAID','PC12000083443')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100095','1154','1223','69','0.1','128407','MA01','01/06/2020','30/06/2020','','UNPAID','PC12000083443')

---Vo Ngoc Hoai Thuong---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100026','100','286','186','0.1','376829','MA01','01/11/2019','30/11/2019','18/12/2019','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100036','286','434','148','0.1','292644','MA01','01/12/2019','31/12/2019','05/01/2020','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100046','434','755','321','0.1','744973','MA01','01/01/2020','31/01/2020','14/02/2020','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100056','755','852','97','0.1','174278','MA01','01/02/2020','29/02/2020','04/03/2020','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100066','852','1392','540','0.1','1440676','MA01','01/03/2020','31/03/2020','16/04/2020','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100076','1392','1561','169','0.1','130910','MA01','01/04/2020','30/04/2020','19/05/2020','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100086','1561','1733','172','0.1','345814','MA01','01/05/2020','31/05/2020','07/06/2020','PAID','PC07000282374')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100096','1733','1974','241','0.1','518199','MA01','01/06/2020','30/06/2020','','UNPAID','PC07000282374')

---Nguyen Ngoc Hai Yen---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100027','100','286','186','0.1','376829','MA01','01/11/2019','30/11/2019','05/12/2019','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100037','286','487','201','0.1','410060','MA01','01/12/2019','31/12/2019','08/01/2020','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100047','487','755','268','0.1','593518','MA01','01/01/2020','31/01/2020','09/02/2020','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100057','755','952','197','0.1','401199','MA01','01/02/2020','29/02/2020','24/03/2020','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100067','952','1192','240','0.1','515409','MA01','01/03/2020','31/03/2020','16/04/2020','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100077','1192','1361','168','0.1','336952','MA01','01/04/2020','30/04/2020','02/05/2020','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100087','1361','1551','190','0.1','385691','MA01','01/05/2020','31/05/2020','04/06/2020','PAID','PE14000006563')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100097','1551','1874','323','0.1','751208','MA01','01/06/2020','30/06/2020','','UNPAID','PE14000006563')

---Tran Do Tuong Vy---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100028','100','243','143','0.1','422362','MA02','01/11/2019','30/11/2019','07/12/2019','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100038','243','387','144','0.1','422294','MA02','01/12/2019','31/12/2019','04/01/2020','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100048','387','555','168','0.1','492677','MA02','01/01/2020','31/01/2020','06/02/2020','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100058','555','752','197','0.1','577722','MA02','01/02/2020','29/02/2020','02/03/2020','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100068','752','1192','440','0.1','1290344','MA02','01/03/2020','31/03/2020','03/04/2020','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100078','1192','1561','369','0.1','1082129','MA02','01/04/2020','30/04/2020','12/05/2020','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100088','1561','1751','190','0.1','557194','MA02','01/05/2020','31/05/2020','04/06/2020','PAID','PE13000008754')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100098','1751','2001','250','0.1','773150','MA02','01/06/2020','30/06/2020','','UNPAID','PE13000008754')

---Thach Diep Thao Nguyen---
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100029','100','243','143','0.1','299185','MA03','01/11/2019','30/11/2019','01/12/2019','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100039','243','387','144','0.1','301277','MA03','01/12/2019','31/12/2019','05/01/2020','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100049','387','555','168','0.1','351490','MA03','01/01/2020','31/01/2020','05/02/2020','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100059','555','752','197','0.1','412163','MA03','01/02/2020','29/02/2020','07/03/2020','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100069','752','1292','540','0.1','1129788','MA03','01/03/2020','31/03/2020','05/04/2020','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100079','1292','1761','469','0.1','981242','MA03','01/04/2020','30/04/2020','10/05/2020','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100089','1761','2051','290','0.1','606738','MA03','01/05/2020','31/05/2020','05/06/2020','PAID','PB04000657657')
INSERT INTO E_ELECTRICITY_BILL(ELEC_BILL_ID,PreviousValue,CurrentValue,ConsumeValue,VAT,Total,Electricity_Type,FromDate,ToDate,DatePaid,StatusBill,CUS_ID) VALUES('100099','2051','2551','500','0.1','10461000','MA03','01/06/2020','30/06/2020','','UNPAID','PB04000657657')


---E_NOTIFICATION---
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO01','22/12/2020','01/01/2021','AD01',N'MỪNG XUÂN TÂN SỬU 2021, TẬP ĐOÀN ĐIỆN LỰC VIỆT NAM KÍNH CHÚC QUÝ KHÁCH HÀNG SỬ DỤNG ĐIỆN TRÊN TOÀN QUỐC VÀ TOÀN THỂ NGƯỜI LAO ĐỘNG NGÀNH ĐIỆN ĐÓN MỘT MÙA XUÂN MỚI AN LÀNH, SỨC KHOẺ, HẠNH PHÚC VÀ THÀNH CÔNG.')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO02','30/03/2020','15/04/2021','AD02',N'The Youth Union of Ho Chi Minh City Power Corporation donates the rooftop solar power system to Thu Duc Youth Village')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO03','15/02/2019','20/02/2019','AD03',N'Hot sun, Electricity Sector provides cool water free of charge to people in Ho Chi Minh City')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO04','12/02/2021','20/02/2021','AD04',N'Encourage customers to use online electrical services ')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO05','11/02/2020','11/04/2020','AD05', N'To ensure safe and long-term power supply, customers will use electricity in the area of ​​Nong Son district managed by Que Son Electricity. From January 4, 2013 to January 10, 2013, Que Son Electricity carried out maintenance and repair of the electrical system')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO09','01/06/2018','06/06/2018','AD05',N'PCGV tran trong thong bao ke tu thang 07/2018 se khong thu tien tai nha, 
moi Quy KH thanh toan tien dien qua Ngan hang hoac cac diem thu ho. LH 1900545454')

INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO06','01/06/2020','04/06/2021','AD01',N'[CẢNH BÁO GIẢ MẠO WEBSITE CÔNG TY ĐIỆN LỰC]
Hiện nay, Tập đoàn Điện lực Việt Nam (EVN) chỉ sở hữu các trang web tại địa chỉ: http://www.evn.com.vn và http://tietkiemnangluong.vn. 
Do vậy, EVN kính đề nghị các khách hàng sử dụng điện khi có yêu cầu bất kỳ dịch vụ nào của EVN chỉ tra cứu thông tin tại các địa chỉ: http://www.evn.com.vn và http://tietkiemnangluong.vn hoặc liên hệ các Trung tâm Chăm sóc khách hàng thuộc các Tổng Công ty Điện lực để được hỗ trợ
Trân trọng./.')

INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO07','18/06/2020','28/06/2021','AD02',N'[CSKH]
Khi có các yêu cầu về điện, Quý khách hàng vui lòng liên hệ với tổng đài chăm sóc khách hàng của Tổng công ty Điện lực Tp. HCM qua số 
1900545454 để được hướng dẫn trực tiếp hoặc qua website CSKH: https://cskh.hcmpc.vn , email: cskh@hcmpc.com.vn
Trân trọng./.')

INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO08','01/06/2021','07/06/2021','AD03',N'[THÔNG BÁO GIẢM GIÁ ĐIỆN ĐỢT 3]
Để tiếp tục góp phần chia sẻ với những khó khăn của các cơ sở lưu trú du lịch và các cơ sở cách ly, cơ sở y tế phòng chống dịch, 
Tổng công ty Điện lực đã nhanh chóng triển khai chỉ đạo tới các Công ty điện lực trực thuộc giảm giá điện đợt 3 cho khách hàng bị ảnh hưởng của dịch bệnh COVID-19.
Chi tiết xem tại: https://www.evnhanoi.com.vn/cms/news?k=ha-noi-nhung-doi-tuong-nao-duoc-giam-gia-dien-giam-tien-dien-dot-3-do-anh-huong-cua-dich-covid-19 
Trân trọng./.')

INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO08','01/09/2021','18/09/2021','AD04',N'[DỊCH VỤ NHẮN TIN CSKH]
Trung tâm Chăm sóc Khách hàng Tổng Công ty Điện lực triển khai dịch vụ nhắn tin chăm sóc khách hàng.
Khách hàng có thể nhắn tin đăng ký nhận thông báo định kỳ về lịch cúp điện, tiền điện, sản lượng điện tiêu thụ, hoặc có thể nhắn tin tuy vấn từng nội dung đơn lẻ.
Chi tiết xem tại: https://www.cskh.evnspc.vn/TinTuc/ChiTietTinSPC?MA_TBAI=134 
Trân trọng./.')
---E_PAYMENT_SCHEDULE ---


---E_PROVIDER ---
INSERT INTO E_PROVIDER(PRO_ID,NamePRO,PhonePRO,AddressPRO,DateJoined,PromotedBy) VALUES ('PR01','Dang Thanh Thao','0237899515','Cong Ty Dien Mien Nam','08/11/2019','AD01')
INSERT INTO E_PROVIDER(PRO_ID,NamePRO,PhonePRO,AddressPRO,DateJoined,PromotedBy) VALUES ('PR02','Le Xuan Mai','0861545612','Cong Ty Dien Mien Tay','08/12/2019','AD04')
INSERT INTO E_PROVIDER(PRO_ID,NamePRO,PhonePRO,AddressPRO,DateJoined,PromotedBy) VALUES ('PR03','Nguyen Tra My','0285618346','Cong Ty Dien Mien Nam','08/01/2019','AD02')
INSERT INTO E_PROVIDER(PRO_ID,NamePRO,PhonePRO,AddressPRO,DateJoined,PromotedBy) VALUES ('PR04','Dang Thanh Binh','0562584300','Cong Ty Dien Mien Bac','08/02/2019','AD03')
INSERT INTO E_PROVIDER(PRO_ID,NamePRO,PhonePRO,AddressPRO,DateJoined,PromotedBy) VALUES ('PR05','Cao Le Bong','0357951264','Cong Ty Dien Mien Trung','08/03/2019','AD01')

---SELECT FUCNTION---
SELECT * FROM E_ADMIN
SELECT * FROM E_CUSTOMER
SELECT * FROM USER_ACCOUNT
SELECT * FROM E_CREDITCARD
SELECT * FROM E_ELECTRICITY_BILL
SELECT * FROM E_ELECTRICITY_TYPE
SELECT * FROM E_NOTIFICATION
SELECT * FROM E_PAYMENT_SCHEDULE
SELECT * FROM E_PROVIDER


---CONSTRAINT/TRIGGER---

--ngay dang ky phai lon hon ngay sinh cua khach hang--
GO
CREATE TRIGGER TRIGGER_DATEREGISTER_BIRTHDAY ON E_CUSTOMER
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @DateRegister SMALLDATETIME, 
			@Birthday SMALLDATETIME
	
	SELECT @Birthday = Birthday, @DateRegister = DateRegister
	FROM INSERTED

	IF(@DateRegister < @Birthday)
	BEGIN 
        PRINT 'Error: DateRegister > Birthday [E_CUSTOMER]'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END

-- Gioi tinh cua khach hang chi duoc la "Nam" "Nu"
GO
CREATE TRIGGER TRIGGER_GENDER ON E_CUSTOMER
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @Gender VARCHAR(5)
	
	SELECT @Gender = Gender
	FROM INSERTED

	IF(@Gender != 'Nam' OR @Gender != 'Nu')
	BEGIN 
        PRINT 'Error: Gioi tinh cua khach hang chi co the la: Nam / Nu [E_CUSTOMER]'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END

INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('CUS6','Tran Ngoc Mai','cus06','0123789546','34TRUONG DINH','VN31818633','KHAC','20/05/2001','Mien Bac')


-- Cardholdername phai trung voi ten khach hang --
GO
CREATE TRIGGER TRIGGER_CARDHOLDERNAME_CUSTOMERNAME ON E_CREDITCARD
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @CustomerName VARCHAR(40), 
			@CardHolderName VARCHAR(40),
			@CustomerID CHAR(4),
			@CreditCardID CHAR(4)
	
	SELECT @CardHolderName = CardHolderName, @CustomerID = CUS_ID
	FROM INSERTED

	SELECT @CustomerName = FullnameCUS
	FROM E_CUSTOMER
	WHERE CUS_ID = @CustomerID

	IF(@CardHolderName != @CustomerName)
	BEGIN 
        PRINT 'Error: Card Holder Name phai trung voi Customer Name [E_CREDITCARD]'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END


--FromDate < ToDate--
GO
CREATE TRIGGER TRIGGER_FROMDATE_TODATE ON E_ELECTRICITY_BILL
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @FromDate SMALLDATETIME, 
			@ToDate SMALLDATETIME
	
	SELECT @FromDate = FromDate, @ToDate = ToDate
	FROM INSERTED

	IF(@ToDate < @FromDate)
	BEGIN 
        PRINT 'Error: FromDate phai lon hon ToDate [E_ELECTRICITY_BILL]'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END

-- Ngay gui phai lon hon ngay tao--
GO
CREATE TRIGGER TRIGGER_DATECREATED_DATESENT ON E_NOTIFICATION
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @DateCreated SMALLDATETIME, 
			@DateSent SMALLDATETIME
	
	SELECT @DateCreated = DateCreated, @DateSent = DateSent
	FROM INSERTED

	IF(@DateSent < @DateCreated)
	BEGIN 
        PRINT 'Error: DateSent phai lon hon hoac bang DateCreated [E_NOTIFICATION]'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END

--DateJoined < today--
GO
CREATE TRIGGER TRIGGER_DATEJOIN ON E_PROVIDER
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @DateJoin SMALLDATETIME
	
	SELECT @DateJoin = DateJoined
	FROM INSERTED

	IF(@DateJoin > GETDATE())
	BEGIN 
        PRINT 'Error: DateJoin phai nho hon hoac bang ngay he thong'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END


--DatePromote < today
GO
CREATE TRIGGER TRIGGER_DATEPROMOTE ON E_ADMIN
FOR INSERT, UPDATE 
AS
BEGIN
	DECLARE @DatePROMOTE SMALLDATETIME
	
	SELECT @DatePROMOTE = DatePromote
	FROM INSERTED

	IF(@DatePROMOTE > GETDATE())
	BEGIN 
        PRINT 'Error: DatePromote phai nho hon hoac bang ngay he thong'
        ROLLBACK TRANSACTION 
    END
    ELSE
    BEGIN
        PRINT 'Success: Du lieu duoc them / cap nhat thanh cong'
    END
END
