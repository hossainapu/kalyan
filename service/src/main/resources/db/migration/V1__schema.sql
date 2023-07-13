CREATE TABLE users (
  id serial,
  name varchar(40) NOT NULL,
  username varchar(15) UNIQUE NOT NULL,
  email varchar(40) UNIQUE NOT NULL,
  password varchar(100) NOT NULL,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);


CREATE TABLE roles (
  id serial NOT NULL,
  name varchar(60) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE user_roles (
  id integer not null primary key,
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
  CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE application_info
(
    id serial NOT NULL,
    office_code varchar(50),
    name_en varchar(100)  NOT NULL,
    name_bn varchar(150) ,
    father_en varchar(100) ,
    father_bn varchar(100) ,
    spouse_en varchar(100) ,
    spouse_bn varchar(100) ,
    date_of_birth date,
    nid varchar(20) ,
    contact_number varchar(16) ,
    emergency_contact varchar(16) ,
    present_division varchar(100) ,
    present_district varchar(100) ,
    present_thana varchar(100) ,
    present_address_line varchar(100) ,
    permanent_division varchar(100) ,
    permanent_district varchar(100) ,
    permanent_thana varchar(100) ,
    permanent_address_line varchar(100) ,
    number_of_emi integer,
    number_of_emi_paid integer,
    application_type varchar(50) ,
    photo bytea,
    signature bytea,
    language integer,
    status integer,
    created_by varchar(100) ,
    updated_by varchar(100) ,
    uploaded_by varchar(100),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    uploaded_date timestamp without time zone,
    gender varchar(255),
    reference_number varchar(50),
    blood_group varchar(20),
    marital_status varchar(20),
    emergency_name varchar(20),
    emergency_relationship varchar(20),
    CONSTRAINT application_info_pkey PRIMARY KEY (id)
);




CREATE TABLE application_info_enroll
(
    id serial NOT NULL,
    office_code varchar(50),
    circle_code varchar(10),
    name_en varchar(100),
    name_bn varchar(150),
    father_en varchar(100),
    father_bn varchar(100),
    spouse_en varchar(100),
    spouse_bn varchar(100),
    date_of_birth date,
    nid varchar(20),
    contact_number varchar(16),
    emergency_contact varchar(16),
    present_division varchar(100),
    present_district varchar(100),
    present_thana varchar(100),
    present_address_line varchar(100),
    permanent_division varchar(100),
    permanent_district varchar(100),
    permanent_thana varchar(100),
    permanent_address_line varchar(100),
    number_of_emi integer,
    number_of_emi_paid integer,
    application_type varchar(50),
    photo bytea,
    signature bytea,
    language integer,
    status integer,
    created_by varchar(100),
    updated_by varchar(100),
    uploaded_by varchar(100),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    uploaded_date timestamp without time zone,
    gender varchar(255),
    reference_number varchar(50),
    blood_group varchar(20),
    marital_status varchar(20),
    emergency_name varchar(20),
    emergency_relationship varchar(20),
    uuid varchar(50),
    bank_transaction_number varchar(30),
    CONSTRAINT application_info_enroll_pkey PRIMARY KEY (id)
);

ALTER TABLE application_info_enroll OWNER to postgres;



CREATE TABLE authority(
    id serial NOT NULL,
    name_en varchar(50) NOT NULL,
    name_bn varchar(100),
    code varchar(10),
    CONSTRAINT authority_pkey PRIMARY KEY (id),
    CONSTRAINT authority_code_key UNIQUE (code)

);



CREATE TABLE selected_authority
(
    id serial NOT NULL,
    code varchar(10) NOT NULL,
    circle varchar(2) NOT NULL,
    CONSTRAINT selected_authority_pkey PRIMARY KEY (id),
    CONSTRAINT selected_authority_code_key UNIQUE (code)

);



CREATE TABLE user_info
(
    id serial NOT NULL,
    name_en varchar(100)  NOT NULL,
    user_name varchar(100)  NOT NULL,
    password varchar(100)  NOT NULL,
    user_type integer NOT NULL DEFAULT 1,
    status integer,
    CONSTRAINT user_info_pkey PRIMARY KEY (id),
    CONSTRAINT user_info_user_name_key UNIQUE (user_name)

);



CREATE TABLE division(
    id serial NOT NULL,
    name_en varchar(100) ,
    name_bn varchar(150) ,
    CONSTRAINT division_pkey PRIMARY KEY (id)
);


CREATE TABLE district
(
    id serial NOT NULL,
    division_id integer NOT NULL,
    name_en varchar(100) ,
    name_bn varchar(150) ,
    CONSTRAINT district_pkey PRIMARY KEY (id)
);



CREATE TABLE thana
(
    id serial NOT NULL,
    district_id integer NOT NULL,
    name_en varchar(100) ,
    name_bn varchar(150) ,
    CONSTRAINT thana_pkey PRIMARY KEY (id)
);



Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Dhaka','ঢাকা',1);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Chittagong','চট্টগ্রাম',3);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Rajshahi','রাজশাহী',2);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Khulna','খুলনা',4);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Barisal','বরিশাল',6);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Sylhet','সিলেট',5);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Rangpur','রংপুর',8);
Insert into DIVISION (NAME_EN,NAME_BN,ID) values ('Mymensingh','ময়মনসিংহ',7);



Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Barguna','বরগুনা','6',52);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Barisal','বরিশাল','6',51);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Bhola','ভোলা','6',50);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Jhalokati','ঝালকাঠি','6',49);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Patuakhali','পটুয়াখালি','6',48);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Pirojpur','পিরোজপুর','6',47);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Bandarban','বান্দরবান','3',30);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Brahmanbaria','ব্রাহ্মণবাড়িয়া','3',29);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Chandpur','চাঁদপুর','3',28);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Chittagong','চট্টগ্রাম','3',27);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Comilla','কুমিল্লা','3',26);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Cox''s Bazar','কক্সবাজার','3',25);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Feni','ফেনী','3',24);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Khagrachhari','খাগড়াছড়ি','3',31);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Lakshmipur','লক্ষ্মীপুর','3',22);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Noakhali','নোয়াখালি','3',32);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Rangamati','রাঙ্গামাটি','3',23);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Dhaka','ঢাকা','1',2);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Faridpur','ফরিদপুর','1',1);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Gazipur','গাজীপুর','1',3);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Gopalganj','গোপালগঞ্জ','1',4);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Kishoreganj','কিশোরগঞ্জ','1',5);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Madaripur','মাদারিপুর','1',6);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Manikganj','মানিকগঞ্জ','1',7);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Munshiganj','মুন্সিগঞ্জ','1',12);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Narayanganj','নারায়ণগঞ্জ','1',11);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Narsingdi','নরসিংদি','1',10);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Rajbari','রাজবাড়ি','1',9);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Shariatpur','শরিয়তপুর','1',8);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Tangail','টাঙ্গাইল','1',13);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Bagerhat','বাগেরহাট','4',37);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Chuadanga','চুয়াডাঙ্গা','4',41);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Jessore','যশোর','4',33);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Jhenaidah','ঝিনাইদহ','4',34);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Khulna','খুলনা','4',35);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Kushtia','কুষ্টিয়া','4',36);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Magura','মাগুরা','4',42);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Meherpur','মেহেরপুর','4',38);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Narail','নড়াইল','4',39);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Satkhira','সাতক্ষীরা','4',40);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Jamalpur','জামালপুর','7',56);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Mymensingh','ময়মনসিংহ','7',55);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Netrokona','নেত্রকোনা','7',54);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Sherpur','শেরপুর','7',53);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Bogra','বগুড়া','2',18);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Joypurhat','জয়পুরহাট','2',21);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Naogaon','নওগাঁ','2',14);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Natore','নাটোর','2',15);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Chapainawabganj','চাঁপাইনবাবগঞ্জ','2',16);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Pabna','পাবনা','2',17);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Rajshahi','রাজশাহী','2',20);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Sirajganj','সিরাজগঞ্জ','2',19);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Dinajpur','দিনাজপুর','8',64);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Gaibandha','গাইবান্ধা','8',63);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Kurigram','কুড়িগ্রাম','8',62);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Lalmonirhat','লালমনিরহাট','8',61);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Nilphamari','নীলফামারি','8',57);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Panchagarh','পঞ্চগড়','8',58);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Rangpur','রংপুর','8',59);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Thakurgaon','ঠাকুরগাঁও','8',60);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Habiganj','হবিগঞ্জ','5',46);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Moulvibazar','মৌলভীবাজার','5',45);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Sunamganj','সুনামগঞ্জ','5',43);
Insert into DISTRICT (NAME_EN,NAME_BN,DIVISION_ID,ID) values ('Sylhet','সিলেট','5',44);



Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (1,'Alfadanga','Alfadanga',1);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (2,'Bhanga','Bhanga',1);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (3,'Boalmari','Boalmari',1);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (4,'Charbhadrasan','Charbhadrasan',1);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (5,'Faridpur Sadar','Faridpur Sadar',1);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (6,'Madhukhali','Madhukhali',1);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (7,'Adabor','আদাবর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (8,'Badda','বাড্ডা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (9,'Bangshal ','বংশাল',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (10,'Biman Bandar',' বিমান বন্দর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (11,'Cantonment','ক্যান্টনমেন্ট',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (12,'Chawkbazar ','চকবাজার',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (13,'Dakshinkhan ','দক্ষিণখান',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (14,'Darus Salam ','দারুস সালাম',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (15,'Demra','ডেমরা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (16,'Dhanmondi','ধানমন্ডি',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (17,'Gendaria ','গেন্ডারিয়া',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (18,'Gulshan','গুলশান',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (19,'Hazaribagh','হাজারীবাগ',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (20,'Jatrabari ','যাত্রাবাড়ি',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (21,'Kadamtali ','কদমতলী',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (22,'Kafrul','কাফরুল',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (23,'Kalabagan ','কলাবাগান',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (24,'Kamrangirchar',' কামরাঙ্গিরচর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (25,'Keraniganj','কেরানীগঞ্জ উপজেলা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (26,'Khilgaon','খিলগাঁও',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (27,'Khilkhet',' খিলক্ষেত',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (28,'Kotwali','কোতোয়ালী',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (29,'Lalbagh','লালবাগ',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (30,'Mirpur','মিরপুর মডেল',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (31,'Mohammadpur','মোহাম্মদপুর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (32,'Motijheel','মতিঝিল',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (33,'New Market ','নিউমার্কেট',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (34,'Pallabi','পল্লবী',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (35,'Paltan ','পল্টন',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (36,'Ramna','রমনা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (37,'Rampura ','রামপুরা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (38,'Sabujbagh','সবুজবাগ',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (39,'Savar','সাভার উপজেলা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (40,'Shah Ali','শাহ আলী',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (41,'Shahbagh ','শাহবাগ',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (42,'Sher-e-Bangla Nagar',' শেরেবাংলা নগর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (43,'Shyampur','শ্যামপুর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (44,'Sutrapur','সূত্রাপুর',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (45,'Tejgaon','তেজগাঁও',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (46,'Tejgaon',' তেজগাঁও শিল্পাঞ্চল',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (47,'Turag','তুরাগ',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (48,'Uttara','উত্তরা',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (49,'Uttar Khan','উত্তর খান',2);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (50,'Gazipur Sadar','গাজীপুর সদর',3);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (51,'Kapasia','কাপাসিয়া',3);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (52,'Tongi town','টঙ্গী শহর',3);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (53,'Sripur','শ্রীপুর',3);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (54,'Kaliganj','কালিগঞ্জ',3);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (55,'Kaliakior','কালিয়াকৈর',3);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (56,'Gopalganj Sadar','গোপালগঞ্জ সদর',4);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (57,'Tungipara','টুংগীপাড়া',4);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (58,'Kotalipara','কোটালীপাড়া',4);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (59,'Kashiani','কাশিয়ানী',4);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (60,'Muksudpur','মুকসুদপুর',4);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (61,'Kuliarchar','Kuliarchar',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (62,'Hossainpur','Hossainpur',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (63,'Pakundia','Pakundia',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (64,'Kishoreganj Sadar','Kishoreganj Sadar',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (65,'Bajitpur','Bajitpur',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (66,'Austagram','Austagram',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (67,'Karimganj','Karimganj',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (68,'Katiadi','Katiadi',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (69,'Tarail','Tarail',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (70,'Itna','Itna',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (71,'Nikli','Nikli',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (72,'Mithamain','Mithamain',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (73,'Bhairab','Bhairab',5);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (74,'Madaripur Sadar','Madaripur Sadar',6);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (75,'Kalkini','Kalkini',6);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (76,'Rajoir','Rajoir',6);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (77,'Shibchar','Shibchar',6);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (78,'Dasar','Dasar',6);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (79,'Ghior','ঘিওর ',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (80,'Daulatpur','দৌলতপুর ',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (81,'Manikganj Sadar','মানিকগঞ্জ সদর ',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (82,'Shibalaya','শিবালয় ',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (83,'Saturia','সাটুরিয়া',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (84,'Singair','সিঙ্গাইর',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (85,'Harirampur Upazila','হরিরামপুর',8);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (86,'Baliakandi','বালিয়াকান্দি',9);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (87,'Goalandaghat','গোয়ালন্দঘাট',9);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (88,'Kalukhali','কালুখালী',9);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (89,'Pangsha','পাংশা',9);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (90,'Rajbari Sadar','রাজবাড়ী সদর',9);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (91,'Narsingdi Sadar','নরসিংদী সদর',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (92,'Raipura','রায়পুরা',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (93,'Belabo','বেলাবো',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (94,'Palash','পলাশ',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (95,'Monohardi','মনোহরদী',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (96,'Shibpur','শিবপুর',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (97,'Monohardi','মাধবদী',10);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (98,'Narayanganj Sadar','Narayanganj Sadar',11);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (99,'Sonargaon','Sonargaon',11);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (100,'Bandar','Bandar',11);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (101,'Araihazar','Araihazar',11);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (102,'Rupganj','Rupganj',11);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (103,'Munshiganj Sadar','মুন্সিগঞ্জ সদর',12);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (104,'Tongibari','টঙ্গীবাড়ী',12);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (105,'Sreenagar','শ্রীনগর',12);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (106,'Lohajang','লৌহজং',12);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (107,'Gazaria','গজারিয়া',12);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (108,'Serajdikhan','সিরাজদীখান',12);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (109,'Basail','Basail',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (110,'Bhuapur','Bhuapur',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (111,'Delduar','Delduar',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (112,'Dhanbari','Dhanbari',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (113,'Ghatail','Ghatail',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (114,'Gopalpur','Gopalpur',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (115,'Kalihati','Kalihati',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (116,'Madhupur','Madhupur',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (117,'Mirzapur','Mirzapur',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (118,'Nagarpur','Nagarpur',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (119,'Sakhipur','Sakhipur',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (120,'Tangail','Tangail',13);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (121,'Atrai','Atrai',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (122,'Badalgachhi','Badalgachhi',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (123,'Dhamoirhat','Dhamoirhat',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (124,'Manda','Manda',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (125,'Mohadevpur','Mohadevpur',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (126,'Naogaon Sadar','Naogaon Sadar',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (127,'Niamatpur','Niamatpur',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (128,'Patnitala','Patnitala',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (129,'Porsha','Porsha',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (130,'Raninagar','Raninagar',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (131,'Sapahar','Sapahar',14);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (132,'Natore Sadar','নাটোর সদর',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (133,'Bagatipara','বাগাতিপাড়া',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (134,'Baraigram','বড়াইগ্রাম',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (135,'Gurudaspur','গুরুদাসপুর',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (136,'Lalpur','লালপুর',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (137,'Singra','সিংড়া',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (138,'Naldanga','নলডাঙ্গা',15);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (139,'Chapai Nawabganj Sadar','চাঁপাইনবাবগঞ্জ ',16);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (140,'Gomostapur','গোমস্তাপুর',16);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (143,'Nachol','নাচোল',16);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (142,'Bholahat','ভোলাহাট',16);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (141,'Shibganj','শিবগঞ্জ',16);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (144,'Dogachi','Dogachi',16);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (145,'Atgharia','আটঘরিয়া',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (146,'Ishwardi','ঈশ্বরদী',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (147,'Chatmohar','চাটমোহর',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (148,'Pabna Sadar','পাবনা সদর',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (149,'Faridpur','ফরিদপুর',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (150,'Bera','বেড়া',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (151,'Bhangura','ভাঙ্গুড়া',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (152,'Santhia','সাঁথিয়া',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (153,'Sujanagar','সুজানগর',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (154,'Aminpur','আমিনপুর',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (155,'Ataikula','আতাইকুলা',17);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (156,'Adamdighi','Adamdighi',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (157,'Bogra Sadar','Bogra Sadar',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (158,'Dhunat','Dhunat',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (159,'Dhupchanchia','Dhupchanchia',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (160,'Gabtali','Gabtali',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (161,'Kahaloo','Kahaloo',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (162,'Nandigram','Nandigram',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (163,'Sariakandi','Sariakandi',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (164,'Shajahanpur','Shajahanpur',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (165,'Sherpur','Sherpur',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (166,'Shibganj','Shibganj',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (167,'Sonatala','Sonatala',18);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (168,'Belkuchi','Belkuchi',19);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (169,'Chauhali','Chauhali',19);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (170,'Kamarkhanda','Kamarkhanda',19);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (171,'Kazipur','Kazipur',19);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (172,'Raiganj','Raiganj',19);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (173,'Shahjadpur','Shahjadpur',19);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (174,'Bagha','Bagha',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (175,'Bagmara','Bagmara',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (176,'Charghat','Charghat',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (177,'Durgapur','Durgapur',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (178,'Godagaɽi','Godagaɽi',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (179,'Mohanpur','Mohanpur',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (180,'Paba','Paba',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (181,'Puthia','Puthia',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (182,'Tanore','Tanore',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (183,'Boalia','Boalia',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (184,'Matihar','Matihar',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (185,'Rajpara','Rajpara',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (186,'Shah Makdam','Shah Makdam',20);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (187,'Joypurhat','জয়পুরহাট সদর',21);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (188,'Akkelpur','আক্কেলপুর',21);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (189,'Kalai','কালাই',21);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (190,'Khetlal','ক্ষেতলাল',21);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (191,'Panchbibi','পাঁচবিবি',21);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (192,'Kamalnagar','Kamalnagar',22);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (193,'Lakshmipur Sadar','Lakshmipur Sadar',22);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (194,'Raipur','Raipur',22);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (195,'Ramganj','Ramganj',22);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (196,'Ramgati','Ramgati',22);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (202,'Bagaichhari','Bagaichhari',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (203,'Barkal','Barkal',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (204,'Belaichhari','Belaichhari',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (205,'Juraichhari','Juraichhari',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (206,'Kaptai','Kaptai',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (207,'Kawkhali','Kawkhali',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (208,'Langadu','Langadu',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (209,'Naniarchar','Naniarchar',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (210,'Rajasthali','Rajasthali',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (211,'Rangamati Sadar','Rangamati Sadar',23);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (212,'Chhagalnaiya','Chhagalnaiya',24);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (213,'Daganbhuiyan','Daganbhuiyan',24);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (214,'Feni Sadar','Feni Sadar',24);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (215,'Fulgazi','Fulgazi',24);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (216,'Parshuram','Parshuram',24);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (217,'Sonagazi','Sonagazi',24);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (218,'Badarkhali','Badarkhali',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (219,'Chakaria','Chakaria',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (220,'Cox''s Bazar Sadar','Cox''s Bazar Sadar',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (221,'Kutubdia','Kutubdia',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (222,'Maheshkhali','Maheshkhali',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (223,'Pekua','Pekua',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (224,'Ramu','Ramu',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (225,'Teknaf','Teknaf',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (226,'Ukhia','Ukhia',25);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (227,'Barura','Barura',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (228,'Brahmanpara','Brahmanpara',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (229,'Burichang','Burichang',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (230,'Chandina','Chandina',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (231,'Chapitala Union Parishad','Chapitala Union Parishad',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (232,'Chauddagram','Chauddagram',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (233,'Chauddagram','Chauddagram',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (234,'Comilla Adarsha Sadar','Comilla Adarsha Sadar',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (235,'Comilla Sadar Dakshin','Comilla Sadar Dakshin',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (236,'Daudkandi','Daudkandi',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (237,'Debidwar','Debidwar',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (238,'Homna','Homna',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (239,'Laksham','Laksham',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (240,'Lalmai','Lalmai',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (241,'Meghna','Meghna',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (242,'Monohorgonj','Monohorgonj',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (243,'Muradnagar','Muradnagar',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (244,'Nangalkot','Nangalkot',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (245,'Titas','Titas',26);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (246,'Akbarshah','Akbarshah',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (247,'Bakoliya','Bakoliya',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (248,'Bandar','Bandar',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (249,'Bayazid','Bayazid',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (250,'Bhujpur','Bhujpur',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (251,'Chandgaon','Chandgaon',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (252,'Double Mooring','Double Mooring',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (253,'EPZ','EPZ',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (254,'Halishahar','Halishahar',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (255,'Karnaphuli','Karnaphuli',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (256,'Khulshi','Khulshi',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (257,'Kotwali','Kotwali',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (258,'Pahartali','Pahartali',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (259,'Panchlaish','Panchlaish',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (260,'Patenga','Patenga',27);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (262,'Chandpur Sadar','Chandpur Sadar',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (263,'Faridganj','Faridganj',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (264,'Haimchar','Haimchar',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (265,'Haziganj','Haziganj',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (266,'Kachua','Kachua',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (267,'Matlab South','Matlab South',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (268,'Matlab Uttar','Matlab Uttar',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (269,'Shahrasti','Shahrasti',28);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (270,'Akhaura','Akhaura',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (271,'Ashuganj','Ashuganj',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (272,'Bancharampur','Bancharampur',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (273,'Bijoynagar','Bijoynagar',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (274,'Brahmanbaria Sadar','Brahmanbaria Sadar',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (275,'Kasba','Kasba',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (276,'Nasirnagar','Nasirnagar',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (277,'Sarail','Sarail',29);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (278,'Ali Kadam','Ali Kadam',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (279,'Bandarban Sadar','Bandarban Sadar',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (280,'Lama','Lama',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (281,'Naikhongchhari','Naikhongchhari',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (282,'Rowangchhari','Rowangchhari',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (283,'Ruma','Ruma',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (284,'Thanchi','Thanchi',30);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (285,'Dighinala','Dighinala',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (286,'Khagrachhari','Khagrachhari',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (287,'Lakshmichhari','Lakshmichhari',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (288,'Mahalchhari','Mahalchhari',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (289,'Manikchhari','Manikchhari',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (290,'Matiranga','Matiranga',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (291,'Panchhari','Panchhari',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (292,'Ramgarh','Ramgarh',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (293,'Guimara','Guimara',31);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (294,'Begumganj','Begumganj',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (295,'Chatkhil','Chatkhil',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (296,'Companiganj','Companiganj',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (297,'Hatiya','Hatiya',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (298,'Kabirhat','Kabirhat',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (299,'Noakhali Sadar','Noakhali Sadar',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (300,'Senbagh','Senbagh',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (301,'Sonaimuri','Sonaimuri',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (302,'Suborno Char','Suborno Char',32);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (303,'Abhaynagar','Abhaynagar',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (304,'Bagherpara','Bagherpara',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (305,'Chaugachha','Chaugachha',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (306,'Jessore Sadar','Jessore Sadar',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (307,'Jhikargacha','Jhikargacha',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (308,'Keshabpur','Keshabpur',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (309,'Manirampur','Manirampur',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (310,'Sharsha','Sharsha',33);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (311,'Harinakunda','Harinakunda',34);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (312,'Jhenaidah Sadar','Jhenaidah Sadar',34);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (313,'Kaliganj','Kaliganj',34);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (314,'Kotchandpur','Kotchandpur',34);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (315,'Maheshpur','Maheshpur',34);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (316,'Shailkupa','Shailkupa',34);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (317,'Batiaghata','Batiaghata',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (318,'Dacope','Dacope',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (319,'Daulatpur','Daulatpur',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (320,'Dighalia','Dighalia',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (321,'Dumuria','Dumuria',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (322,'Harintana','Harintana',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (323,'Khalishpur','Khalishpur',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (324,'Khan Jahan Ali','Khan Jahan Ali',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (325,'Koyra','Koyra',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (326,'Paikgachha','Paikgachha',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (327,'Phultala','Phultala',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (328,'Rupsa','Rupsa',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (329,'Sonadanga','Sonadanga',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (330,'Terokhada','Terokhada',35);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (331,'Bheramara','Bheramara',36);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (332,'Daulatpur','Daulatpur',36);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (333,'Khoksa','Khoksa',36);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (334,'Kumarkhali','Kumarkhali',36);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (335,'Kushtia Sadar','Kushtia Sadar',36);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (336,'Mirpur','Mirpur',36);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (337,'Bagerhat Sadar','Bagerhat Sadar',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (338,'Chitalmari','Chitalmari',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (339,'Fakirhat','Fakirhat',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (340,'Kachua','Kachua',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (341,'Mollahat','Mollahat',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (342,'Mongla','Mongla',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (343,'Morrelganj','Morrelganj',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (344,'Rampal','Rampal',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (345,'Sarankhola','Sarankhola',37);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (346,'Gangni','Gangni',38);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (347,'Meherpur Sadar','Meherpur Sadar',38);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (348,'Mujibnagar','Mujibnagar',38);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (349,'Kalia','Kalia',39);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (350,'Lohagara','Lohagara',39);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (351,'Naragati','Naragati',39);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (352,'Narail Sadar','Narail Sadar',39);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (353,'Assasuni','Assasuni',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (354,'Debhata','Debhata',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (355,'Kalaroa','Kalaroa',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (356,'Kaliganj','Kaliganj',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (357,'Satkhira Sadar','Satkhira Sadar',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (358,'Shyamnagar','Shyamnagar',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (359,'Tala','Tala',40);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (360,'Alamdanga','Alamdanga',41);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (361,'Chuadanga Sadar','Chuadanga Sadar',41);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (362,'Damurhuda','Damurhuda',41);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (363,'Jibannagar','Jibannagar',41);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (364,'Magura Sadar','Magura Sadar',42);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (365,'Mohammadpur','Mohammadpur',42);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (366,'Shalikha','Shalikha',42);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (367,'Sreepur','Sreepur',42);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (368,'Bishwamvarpur','Bishwamvarpur',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (369,'Chhatak','Chhatak',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (370,'Dakshin Sunamganj','Dakshin Sunamganj',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (371,'Derai','Derai',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (372,'Dharamapasha','Dharamapasha',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (373,'Dowarabazar','Dowarabazar',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (374,'Jagannathpur','Jagannathpur',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (375,'Jamalganj','Jamalganj',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (376,'Sullah','Sullah',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (377,'Sunamganj Sadar','Sunamganj Sadar',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (378,'Tahirpur','Tahirpur',43);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (379,'Companiganj','Companiganj',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (380,'Dakshin Surma','Dakshin Surma',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (381,'Fenchuganj','Fenchuganj',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (382,'Gowainghat','Gowainghat',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (383,'Jaintiapur','Jaintiapur',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (384,'Kanaighat','Kanaighat',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (385,'Sylhet Sadar','Sylhet Sadar',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (386,'Zakiganj','Zakiganj',44);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (387,'Barlekha','Barlekha',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (388,'Juri','Juri',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (389,'Kamalganj','Kamalganj',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (390,'Kulaura','Kulaura',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (391,'Moulvibazar Sadar','Moulvibazar Sadar',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (392,'Rajnagar','Rajnagar',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (393,'Srimangal','Srimangal',45);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (394,'Ajmiriganj','Ajmiriganj',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (395,'Bahubal','Bahubal',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (396,'Baniachong','Baniachong',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (397,'Chunarughat','Chunarughat',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (398,'Habiganj Sadar','Habiganj Sadar',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (399,'Lakhai','Lakhai',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (400,'Madhabpur','Madhabpur',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (401,'Nabiganj','Nabiganj',46);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (402,'Bhandaria','Bhandaria',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (403,'Kawkhali','Kawkhali',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (404,'Mathbaria','Mathbaria',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (405,'Nazirpur','Nazirpur',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (406,'Nesarabad','Nesarabad',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (407,'Pirojpur Sadar','Pirojpur Sadar',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (408,'Indurkani','Indurkani',47);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (409,'Bauphal','Bauphal',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (410,'Dashmina','Dashmina',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (411,'Dumki','Dumki',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (412,'Galachipa','Galachipa',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (413,'Kalapara','Kalapara',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (414,'Mirzaganj','Mirzaganj',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (415,'Patuakhali Sadar','Patuakhali Sadar',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (416,'Rangabali','Rangabali',48);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (417,'Jhalokati Sadar','Jhalokati Sadar',49);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (418,'Kathalia','Kathalia',49);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (419,'Nalchity','Nalchity',49);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (420,'Rajapur','Rajapur',49);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (421,'Bhola Sadar','Bhola Sadar',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (422,'Daulatkhan','Daulatkhan',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (423,'Burhanuddin','Burhanuddin',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (424,'Tazumuddin','Tazumuddin',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (425,'Lalmohan','Lalmohan',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (426,'Char Fasson','Char Fasson',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (427,'Manpura','Manpura',50);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (428,'Agailjhara','Agailjhara',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (429,'Babuganj','Babuganj',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (430,'Bakerganj','Bakerganj',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (431,'Banaripara','Banaripara',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (432,'Barisal Sadar','Barisal Sadar',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (433,'Gournadi','Gournadi',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (434,'Hizla','Hizla',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (435,'Mehendiganj','Mehendiganj',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (436,'Muladi','Muladi',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (437,'Wazirpur','Wazirpur',51);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (438,'Amtali','Amtali',52);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (439,'Bamna','Bamna',52);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (440,'Barguna Sadar','Barguna Sadar',52);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (441,'Betagi','Betagi',52);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (442,'Patharghata','Patharghata',52);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (443,'Taltali','Taltali',52);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (444,'Jhenaigati','Jhenaigati',53);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (445,'Nakla','Nakla',53);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (446,'Nalitabari','Nalitabari',53);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (447,'Sherpur Sadar','Sherpur Sadar',53);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (448,'Sreebardi','Sreebardi',53);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (449,'Atpara','Atpara',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (450,'Barhatta','Barhatta',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (451,'Durgapur','Durgapur',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (452,'Kalmakanda','Kalmakanda',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (453,'Kendua','Kendua',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (454,'Khaliajuri','Khaliajuri',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (455,'Madan','Madan',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (456,'Mohanganj','Mohanganj',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (457,'Netrakona Sadar','Netrakona Sadar',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (458,'Purbadhala','Purbadhala',54);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (459,'Bhaluka','Bhaluka',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (460,'Dhobaura','Dhobaura',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (461,'Fulbaria','Fulbaria',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (462,'Gafargaon','Gafargaon',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (463,'Gouripur','Gouripur',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (464,'Haluaghat','Haluaghat',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (465,'Ishwarganj','Ishwarganj',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (466,'Muktagachha','Muktagachha',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (467,'Mymensingh Sadar','Mymensingh Sadar',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (468,'Nandail','Nandail',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (469,'Phulpur','Phulpur',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (470,'Tara Khanda','Tara Khanda',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (471,'Trishal','Trishal',55);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (472,'Baksiganj','Baksiganj',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (473,'Dewanganj','Dewanganj',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (474,'Islampur','Islampur',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (475,'Jamalpur Sadar','Jamalpur Sadar',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (476,'Madarganj','Madarganj',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (477,'Melandaha','Melandaha',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (478,'Sarishabari','Sarishabari',56);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (479,'Dimla','Dimla',57);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (480,'Domar','Domar',57);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (481,'Jaldhaka','Jaldhaka',57);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (482,'Kishoreganj','Kishoreganj',57);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (483,'Nilphamari Sadar','Nilphamari Sadar',57);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (484,'Saidpur','Saidpur',57);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (485,'Atwari','Atwari',58);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (486,'Boda','Boda',58);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (487,'Debiganj','Debiganj',58);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (488,'Panchagarh Sadar','Panchagarh Sadar',58);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (489,'Tetulia','Tetulia',58);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (490,'Badarganj','Badarganj',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (491,'Gangachara','Gangachara',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (492,'Kaunia','Kaunia',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (493,'Mithapukur','Mithapukur',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (494,'Pirgachha','Pirgachha',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (495,'Pirganj','Pirganj',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (496,'Rangpur Sadar','Rangpur Sadar',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (497,'Taraganj','Taraganj',59);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (498,'Thakurgaon Sadar','Thakurgaon Sadar',60);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (499,'Baliadangi','Baliadangi',60);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (500,'Haripur','Haripur',60);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (501,'Ranisankail','Ranisankail',60);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (502,'Pirganj','Pirganj',60);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (503,'Aditmari','Aditmari',61);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (504,'Hatibandha','Hatibandha',61);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (505,'Kaliganj','Kaliganj',61);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (506,'Lalmonirhat Sadar','Lalmonirhat Sadar',61);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (507,'Patgram','Patgram',61);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (508,'Bhurungamari','Bhurungamari',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (509,'Char Rajibpur','Char Rajibpur',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (510,'Chilmari','Chilmari',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (511,'Kurigram Sadar','Kurigram Sadar',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (512,'Nageshwari','Nageshwari',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (513,'Phulbari','Phulbari',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (514,'Rajarhat','Rajarhat',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (515,'Raomari','Raomari',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (516,'Ulipur','Ulipur',62);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (517,'Gaibandha Sadar','Gaibandha Sadar',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (518,'Gobindaganj','Gobindaganj',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (519,'Palashbari','Palashbari',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (520,'Phulchhari','Phulchhari',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (521,'Sadullapur','Sadullapur',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (522,'Saghata','Saghata',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (523,'Sundarganj','Sundarganj',63);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (524,'Biral','Biral',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (525,'Birampur','Birampur',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (526,'Birganj','Birganj',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (527,'Bochaganj','Bochaganj',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (528,'Chirirbandar','Chirirbandar',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (529,'Dinajpur Sadar','Dinajpur Sadar',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (530,'Fulbari','Fulbari',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (531,'Ghoraghat','Ghoraghat',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (532,'Hakimpur','Hakimpur',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (533,'Kaharole','Kaharole',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (534,'Khansama','Khansama',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (535,'Nawabganj','Nawabganj',64);
Insert into THANA (ID,NAME_EN,NAME_BN,DISTRICT_ID) values (536,'Parbatipur','Parbatipur',64);
