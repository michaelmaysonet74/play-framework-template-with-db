CREATE TABLE IF NOT EXISTS play_framwork_template (
    "id" BIGSERIAL NOT NULL,
    "url" VARCHAR NOT NULL,
    "status" VARCHAR NOT NULL,
    CONSTRAINT template_pk PRIMARY KEY ("id")
);

INSERT INTO play_framwork_template ("url", "status") VALUES ('www.google.com', '200 OK');

INSERT INTO play_framwork_template ("url", "status") VALUES ('www.apple.com', '200 OK');
