create table `user` (
  oid bigint primary key auto_increment,
  real_name varchar(20) not null,
  identification_number varchar(18),
  mobile_number varchar(15)
)