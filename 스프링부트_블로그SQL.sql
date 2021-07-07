-- 유저이름@아이피주소
create user 'cot'@'%' identified by 'cot1234';
-- ON DB이름.테이블명
-- TO 유저이름@아이피주소
GRANT ALL PRIVILEGES ON *.* TO 'cot'@'%';
CREATE DATABASE blog CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
use blog;


insert into reply(content, boardId, userId, createDate)
values('첫번째 댓글',1,1,now());
insert into reply(content, boardId, userId, createDate)
values('두번째 댓글',1,1,now());
insert into reply(content, boardId, userId, createDate)
values('세번째 댓글',1,1,now());

commit;