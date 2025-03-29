CREATE TABLE book (
  id                 BIGSERIAL PRIMARY KEY NOT NULL,  -- id 필드를 기본 키로 생성
  author             VARCHAR(255) NOT NULL,
  isbn               VARCHAR(255) UNIQUE NOT NULL,    -- isbn 필드는 고유한 값이어야 한다는 제약 조건
  price              FLOAT8,
  title              VARCHAR(255) NOT NULL,
  created_date       TIMESTAMP NOT NULL,
  last_modified_date TIMESTAMP NOT NULL,
  version            INTEGER NOT NULL
);