DROP TABLE IF EXISTS "public"."student";
CREATE TABLE "public"."student" (
  "id" int4 NOT NULL,
  "first_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "last_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
ALTER TABLE "public"."student" OWNER TO "postgres";

BEGIN;
INSERT INTO "public"."student" VALUES (1, 'John', 'Smith', 'john@gmail.com');
INSERT INTO "public"."student" VALUES (2, 'Sachin', 'Dave', 'sachin@gmail.com');
INSERT INTO "public"."student" VALUES (3, 'Peter', 'Mark', 'peter@gmail.com');
INSERT INTO "public"."student" VALUES (4, 'Martin', 'Smith', 'martin@gmail.com');
INSERT INTO "public"."student" VALUES (5, 'Raj', 'Patel', 'raj@gmail.com');
INSERT INTO "public"."student" VALUES (6, 'Virat', 'Yadav', 'virat@gmail.com');
INSERT INTO "public"."student" VALUES (7, 'Prabhas', 'Shirke', 'prabhas@gmail.com');
INSERT INTO "public"."student" VALUES (8, 'Tina', 'Kapoor', 'tina@gmail.com');
INSERT INTO "public"."student" VALUES (9, 'Mona', 'Sharma', 'mona@gmail.com');
INSERT INTO "public"."student" VALUES (10, 'Rahul', 'Varma', 'rahul@gmail.com');
COMMIT;

ALTER TABLE "public"."student" ADD CONSTRAINT "student_pkey" PRIMARY KEY ("id");
