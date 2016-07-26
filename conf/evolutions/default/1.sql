# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table users (
  id                            bigint not null,
  email                         varchar(255) not null,
  full_name                     varchar(255) not null,
  sha_password                  varbinary(64) not null,
  validated                     boolean not null,
  creation_date                 timestamp,
  create_owner                  varchar(255),
  update_date                   timestamp,
  update_owner                  varchar(255),
  profile                       varchar(2) not null,
  constraint ck_users_profile check (profile in ('R1','R2','R3')),
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id)
);
create sequence users_seq;


# --- !Downs

drop table if exists users;
drop sequence if exists users_seq;

