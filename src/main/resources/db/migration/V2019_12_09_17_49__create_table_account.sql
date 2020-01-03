create table `account` (
  user_id bigint,
  bid varchar(40) not null,
  amount double default 0
)