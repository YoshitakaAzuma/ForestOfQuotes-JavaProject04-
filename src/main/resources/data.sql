
insert into users (username, password_hash, email, authority, banned)
values ('admin','$2a$10$8mPrmVlUm79WUa7v0Ql.Ge9BINKIc42p.3mClWu1V08ohXB.iMbki', 'admin@gmail.com', 'ADMIN',
		 false );
insert into users (username, password_hash, email, authority, banned)
values ('user1','$2a$10$CSDoH4U6iJmc10FywcHQjezbJtgvHGnJr7qUFK8tHQLEWREWka1Qm', 'user1@gmail.com', 'USER',
		 false );
insert into users (username, password_hash, email, authority, limited_period, banned)
values ('user2','$2a$10$DhT1FQMPCa5/D4JFXFY5A.Vrzjxjrw.QEcfh8PtJN3Dcc5YsaXlSW', 'user2@docomo.ne.jp', 'USER',
		 '2024-9-5', false );



insert into quotes (quotes, category, whose, user_id, mine, good, created_at)
values ('人生は、大いなる戦場である', 1,'島崎藤村', 1, false ,0, current_timestamp);
insert into quotes (quotes, category, whose, user_id, mine, good, created_at)
values ('人の一生は重荷を負うて遠き道を 行くがごとし。 急ぐべからず', 1,'徳川家康', 1, false ,0, current_timestamp);
insert into quotes (quotes, category, whose, whose_detail, user_id, mine, good, created_at)
values ('動かない者は、つけられた鎖にも 気づかない', 4,'ローザ・ルクセンブルク', 'ドイツの革命家', 1, false , 0, current_timestamp);
insert into quotes (quotes, category, whose, whose_detail, user_id, mine, good, created_at)
values ('人生なんていわば博打みてーなもんだ。人はみなギャンブラーさ', 1,'長谷川泰三', '漫画『銀魂』', 1, false , 0, current_timestamp);
insert into quotes (quotes, category, whose, user_id, mine, good, created_at)
values ('自分自身を愛していなければ、 結局幸せにはなれないのよ', 8,'レディ・ガガ', 1, false ,0, current_timestamp);


insert into profiles (user_id, favorite_quotes_id, follow, follower)
values (2, 1, 0, 0);
insert into profiles (user_id, favorite_quotes_id, follow, follower)
values (3, 4, 0, 0);

insert into categories (id, category)
values (1,'人生と生き方');
insert into categories (id, category)
values (2,'成功と挑戦');
insert into categories (id, category)
values (3,'愛と人間関係');
insert into categories (id, category)
values (4,'知恵と学び');
insert into categories (id, category)
values (5,'希望と勇気');





