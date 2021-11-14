create table CARDS(
                         id varchar(36) not null primary key,
                         cardTitle varchar(200) not null,
                         cardText varchar (50) not null,
                         cardComments varchar (150),
                         cardCommentsCounter integer,
                         cardRepilies varchar (150);
)