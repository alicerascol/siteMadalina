USE bookster;
GO

-- It is IDENTITY not AUTO_INCREMENT in SQL Server.
-- IDENTITY(1, 1) -> The first one is the start number, the second is the seeding, so (1, 1) means: 1, 2, 3, 4, ...
IF NOT EXISTS (SELECT * FROM sys.objects
                WHERE object_id = OBJECT_ID(N'[dbo].[user_tbl]')
                AND type IN (N'P', N'PC'))
    BEGIN
        EXEC dbo.sp_executesql @statement = N'CREATE TABLE user_tbl (
                id int NOT NULL IDENTITY(1, 1),
                email varchar(50) NOT NULL,
                user_name varchar(50),
                last_name varchar(50),
                first_name varchar(255),
                password varchar(10),
                PRIMARY KEY (id),
                UNIQUE (email)
            );';
    END
GO