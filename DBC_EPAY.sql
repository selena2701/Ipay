CREATE DATABASE DBC
USE DBC

-----CREATE TABLE-----

CREATE TABLE USER_ACCOUNT
(
	Username VARCHAR(40) PRIMARY KEY,
	Password_Login VARCHAR(40),
	RoleUser BIT
)
ALTER TABLE E_ADMIN ADD CONSTRAINT FK_EADMIN_USERACCOUNT FOREIGN KEY(Username) REFERENCES USER_ACCOUNT(Username)
ALTER TABLE E_CUSTOMER ADD CONSTRAINT FK_CUSTOMER_USERACCOUNT FOREIGN KEY(Username) REFERENCES USER_ACCOUNT(Username)

CREATE TABLE E_CUSTOMER
(
	CUS_ID CHAR(20) PRIMARY KEY ,
	FullnameCUS VARCHAR(40),
	Username VARCHAR(40) ,
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
	Username VARCHAR(40),
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

SELECT eb.ELEC_BILL_ID AS "INVOICE ID", cus.FullnameCUS as "PAID BY", EB.Total, eb.DatePaid 
FROM dbo.E_ELECTRICITY_BILL eb, dbo.E_CUSTOMER cus
WHERE eb.CUS_ID=cus.CUS_ID

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
	Detail VARCHAR(8000),
)

CREATE TABLE E_PAYMENT_SCHEDULE
(
	PS_ID CHAR(4) PRIMARY KEY,
	CUS_ID CHAR(20) REFERENCES E_CUSTOMER(CUS_ID),
	DateSet SMALLDATETIME,
	TimeSet TIMESTAMP,
)



---INSERT DATABASE---
SET DATEFORMAT DMY 

---USER_ACCOUNT---
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('cus01',HASHBYTES('SHA1','123456'),0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('user01',HASHBYTES('SHA1','123456'),1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('cus02',HASHBYTES('SHA1','123456'),0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('user02',HASHBYTES('SHA1','123456'),1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('cus03',HASHBYTES('SHA1','123456'),0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('user03',HASHBYTES('SHA1','123456'),1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('cus04',HASHBYTES('SHA1','123456'),0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('user04',HASHBYTES('SHA1','123456'),1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('cus05',HASHBYTES('SHA1','123456'),0)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('user05',HASHBYTES('SHA1','123456'),1)
INSERT INTO USER_ACCOUNT(Username,Password_Login,RoleUser) VALUES ('user07','123456',1)


---E_CUSTOMER---
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('CUS1','Nguyen Van Tin','cus01','0123789546','731TRAN HUNG DAO','VN00225577','Nam','13/04/1999','Mien Tay')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('CUS2','Tran Ngoc Han','cus02','0123789546','23/5NGUYEN TRAI','VN11995241','Nu','07/01/1998','Mien Tay')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('CUS3','Le Van Tho','cus03','0123789546','45NGUYEN CANH CHAN','VN953188463','Nam','23/07/2002','Mien Trung')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('CUS4','Tran Minh Long','cus04','0123789546','50/34LE DAI HANH','VN22183883','Nam','13/04/1999','Mien Nam')
INSERT INTO E_CUSTOMER(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region) VALUES ('CUS5','Tran Ngoc Mai','cus05','0123789546','34TRUONG DINH','VN31818633','Nu','20/05/2001','Mien Bac')


---E_ADMIN---
INSERT INTO E_ADMIN(AD_ID,NameAD,Username,PromotedBy,DatePromote) VALUES ('AD01','Le Thi Hong Cuc','user01','','12/12/2029')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD02','Vu Thuy Duong','user02','user01','02/07/2019')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD03','Le Hoang Thinh','user03','user01','06/02/2020')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD04','Phan Thai Tam','user04','user02','09/10/2020')
INSERT INTO E_ADMIN(AD_ID,NameAD,UserName,PromotedBy,DatePromote) VALUES ('AD05','Le Huynh Lan Ha','user05','user01','07/11/2020')

---E_CREDITCARD---
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,CUS_ID) VALUES ('CC01','Nguyen Van Tin','0943576214','1237896545','Dong A','CUS1')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,CUS_ID) VALUES ('CC02','Tran Ngoc Han','0846154834','7896541230','Sacombank','CUS2')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,CUS_ID) VALUES ('CC03','Le Van Tho','0615465321','8521479632','Vietcombank','CUS3')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,CUS_ID) VALUES ('CC04','Tran Minh Long','0698521472','9513216547','Sacombank','CUS4')
INSERT INTO E_CREDITCARD(CC_ID,CardHolderName,CardNumber,AccountNumber,BankName,CUS_ID) VALUES ('CC05','Tran Ngoc Mai','0236584952','6541230879','Dong A','CUS5')

---E_ELECTRICITY_BILL---
INSERT INTO E_ELECTRICITY_BILL VALUES('BILL01','100','207','110','0.5','199472','MA01','01/04/2021','30/04/2021','01/05/2021','PAID','CUS1')
INSERT INTO E_ELECTRICITY_BILL VALUES('BILL02','100','207','110','0.5','199472','MA01','01/04/2021','30/04/2021','','UNPAID','CUS2')
INSERT INTO E_ELECTRICITY_BILL VALUES('BILL03','100','207','110','0.5','199472','MA01','01/04/2021','30/04/2021','','UNPAID','CUS3')
INSERT INTO E_ELECTRICITY_BILL VALUES('BILL04','100','207','110','0.5','199472','MA01','01/04/2021','30/04/2021','','UNPAID','CUS4')
INSERT INTO E_ELECTRICITY_BILL VALUES('BILL05','8889','8996','107','0.5','199472','MA01','01/04/2021','30/04/2021','01/05/2021','PAID','CUS5')

--- E_ELECTRICITY_TYPE---
INSERT INTO E_ELECTRICITY_TYPE(Electricity_Type,TypeName) VALUES ('MA01','Dien sinh hoat')
INSERT INTO E_ELECTRICITY_TYPE(Electricity_Type,TypeName) VALUES ('MA02','Dien kinh doanh')
INSERT INTO E_ELECTRICITY_TYPE(Electricity_Type,TypeName) VALUES ('MA03','Dien cong cong')

---E_NOTIFICATION---
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO01','22/12/2020','01/01/2021','AD01','MỪNG XUÂN TÂN SỬU 2021, TẬP ĐOÀN ĐIỆN LỰC VIỆT NAM KÍNH CHÚC QUÝ KHÁCH HÀNG SỬ DỤNG ĐIỆN TRÊN TOÀN QUỐC VÀ TOÀN THỂ NGƯỜI LAO ĐỘNG NGÀNH ĐIỆN ĐÓN MỘT MÙA XUÂN MỚI AN LÀNH, SỨC KHOẺ, HẠNH PHÚC VÀ THÀNH CÔNG.')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO02','30/03/2020','15/04/2021','AD02','The Youth Union of Ho Chi Minh City Power Corporation donates the rooftop solar power system to Thu Duc Youth Village')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO03','15/02/2019','20/02/2019','AD03','Hot sun, Electricity Sector provides cool water free of charge to people in Ho Chi Minh City')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO04','12/02/2021','20/02/2021','AD04','Encourage customers to use online electrical services ')
INSERT INTO E_NOTIFICATION(NO_ID,DateCreated,DateSent,EditedBy,Detail) VALUES ('NO05','11/02/2020','11/04/2020','AD05', 'To ensure safe and long-term power supply, customers will use electricity in the area of ​​Nong Son district managed by Que Son Electricity. From January 4, 2013 to January 10, 2013, Que Son Electricity carried out maintenance and repair of the electrical system')

---E_ONLINE_PAYMENT---
INSERT INTO E_ONLINE_PAYMENT(OP_ID,Online_Payment_Provider,Credit_Card_ID,CUS_ID) VALUES ('MM01','MOMO','CC01','CUS1')
INSERT INTO E_ONLINE_PAYMENT(OP_ID,Online_Payment_Provider,Credit_Card_ID,CUS_ID) VALUES ('MM02','ZALOPAY','CC02','CUS2')
INSERT INTO E_ONLINE_PAYMENT(OP_ID,Online_Payment_Provider,Credit_Card_ID,CUS_ID) VALUES ('MM03','MOMO','CC03','CUS3')
INSERT INTO E_ONLINE_PAYMENT(OP_ID,Online_Payment_Provider,Credit_Card_ID,CUS_ID) VALUES ('MM04','ZALOPAY','CC04','CUS4')
INSERT INTO E_ONLINE_PAYMENT(OP_ID,Online_Payment_Provider,Credit_Card_ID,CUS_ID) VALUES ('MM05','MOMO','CC05','CUS5')


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
SELECT * FROM E_ONLINE_PAYMENT
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