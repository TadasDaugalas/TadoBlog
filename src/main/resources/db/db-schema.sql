create table CARD(
                         id varchar(36) not null primary key,
                         cardTitle varchar(200) not null,
                         cardText TEXT not null,
                         cardComments varchar (150),
                         cardCommentsCounter integer,
                         cardRepilies varchar (150);
)