show tables;

select * from companion;
select * from meal;
select * from Food;

create table companion(
	cIdx int not null auto_increment primary key,	-- 회원고유번호
	id varchar(20) not null,											-- 아이디
	pwd varchar(50) not null, 										-- 비밀번호
	name varchar(20) not null, 										-- 이름
	age int default 0,														-- 나이
	gender char(2) default '여자',									-- 성별
	email varchar(50),														-- 이메일	
	tel varchar(20) default '000-0000-0000'				-- 전화번호
);

create table meal(
	mIdx int not null auto_increment primary key,	-- 식사고유번호
	cIdx int not null,								-- 회원고유번호(외래키)
	fIdx int not null,								-- 음식고유번호(외래키)
	meal varchar(20) not null,				-- 식사 종류(아침,점심,저녁,간식)
	mealTime datetime default now(),	-- 식사 일자 및 시간
	mealMenu varchar(100),						-- 식사 메뉴
	aMealKcal double default 0.0,			-- 한 끼 섭취 칼로리
	dayKcal double default 0.0,				-- 하루 섭취 칼로리
	dayGoalKcal double default 0.0,		-- 하루 목표 섭취 칼로리
	mealPhoto varchar(50)							-- 식사 사진
);

select * from meal;
insert into meal (cIdx, fIdx, meal) values (1, 1, '아침' )

create table food(
	fIdx int not null auto_increment primary key,	-- 음식고유번호
	foodName varchar(30) not null, 				-- 음식 이름
	productName varchar(50) not null,			-- 음식의 상품명 또는 식당 이름
	intake double default 0.0,						-- 섭취량
	kcal double default 0.0, 							-- 칼로리
	carbohydrate double default 0.0,			-- 탄수화물
	dietaryFiber double default 0.0,			-- 식이섬유
	sugars double default 0.0,						-- 당류
	protein double default 0.0,						-- 단백질
	fat double default 0.0,								-- 지방
	saturatedFat double default 0.0,			-- 포화지방
	natrium double default 0.0						-- 나트륨
);

select * from food;
desc meal;
drop table meal;

select m.*,f.foodName,f.productName from meal m, food f where m.fidx=f.fIdx order by mIdx desc

