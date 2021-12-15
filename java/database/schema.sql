BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS movies CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS favorites CASCADE;
DROP TABLE IF EXISTS genres_users CASCADE;
DROP TABLE IF EXISTS watch_list;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	user_age int,
	above_17 boolean,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE movies (
        movie_id int NOT NULL,--primary key
        title varchar(100) NOT NULL,
        overview varchar(1000) NOT NULL,
        runtime int NOT NULL,
        director varchar(100),
        actors varchar(100) array[5],
        release_date varchar(20) NOT NULL,
        rating numeric,
        certification varchar(10),
        genres int array[20],
        constraint pk_movies primary key(movie_id)
);

CREATE TABLE genres (
        genre_id int NOT NULL,
        genre_name varchar(50),
        constraint pk_genres primary key(genre_id)

);

CREATE TABLE genres_users (
        genre_id int NOT NULL,
        user_id int NOT NULL,
        
        constraint pk_genres_users primary key(genre_id, user_id),
        constraint fk_genres_users_genre foreign key(genre_id) references genres(genre_id),
        constraint fk_genres_users_user foreign key(user_id) references users(user_id)
);

CREATE TABLE favorites (
        user_id int NOT NULL,
        movie_id int NOT NULL,
        --CONSTRAINT PK_user PRIMARY KEY (user_id)
        constraint fk_favorites_users foreign key(user_id) references users(user_id),
        constraint fk_favorites_movies foreign key(movie_id) references movies(movie_id)
);

CREATE TABLE watch_list (
        user_id int NOT NULL,
        movie_id int NOT NULL,
        --CONSTRAINT PK_user PRIMARY KEY (user_id)
        constraint fk_watch_list_users foreign key(user_id) references users(user_id),
        constraint fk_watch_list_movies foreign key(movie_id) references movies(movie_id)
);


INSERT INTO users (username,password_hash,user_age,above_17,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 18, true, 'ROLE_USER');
INSERT INTO users (username,password_hash,user_age,above_17,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 18, true,'ROLE_ADMIN');

INSERT INTO genres (genre_id, genre_name) VALUES ('28','Action');
INSERT INTO genres (genre_id, genre_name) VALUES ('12','Adventure');
INSERT INTO genres (genre_id, genre_name) VALUES ('16','Animation');
INSERT INTO genres (genre_id, genre_name) VALUES ('35','Comedy');
INSERT INTO genres (genre_id, genre_name) VALUES ('80','Crime');
INSERT INTO genres (genre_id, genre_name) VALUES ('99','Documentary');
INSERT INTO genres (genre_id, genre_name) VALUES ('18','Drama');
INSERT INTO genres (genre_id, genre_name) VALUES ('10751','Family');
INSERT INTO genres (genre_id, genre_name) VALUES ('14','Fantasy');
INSERT INTO genres (genre_id, genre_name) VALUES ('36','History');
INSERT INTO genres (genre_id, genre_name) VALUES ('27','Horror');
INSERT INTO genres (genre_id, genre_name) VALUES ('10402','Music');
INSERT INTO genres (genre_id, genre_name) VALUES ('9648','Mystery');
INSERT INTO genres (genre_id, genre_name) VALUES ('10749','Romance');
INSERT INTO genres (genre_id, genre_name) VALUES ('878','Science Fiction');
INSERT INTO genres (genre_id, genre_name) VALUES ('10770','TV Movie');
INSERT INTO genres (genre_id, genre_name) VALUES ('53','Thriller');
INSERT INTO genres (genre_id, genre_name) VALUES ('10752','War');
INSERT INTO genres (genre_id, genre_name) VALUES ('37','Western');

INSERT INTO movies (movie_id, title, overview, runtime, director, actors, release_date, rating, certification, genres) 
VALUES (580489, 'Venom: Let There Be Carnage', 'After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.', 
        97, 'Andy Serkis', '{"Tom Hardy", "Woody Harrelson", "Michelle Williams", "Naomie Harris", "Reid Scott"}', '2021-09-30', 7.2, 'PG-13', '{878,28,12}');

        
INSERT INTO favorites (user_id, movie_id)
VALUES (1, 580489);



COMMIT TRANSACTION;

