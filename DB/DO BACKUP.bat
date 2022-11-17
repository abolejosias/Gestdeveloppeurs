@echo WELCOME to DO BACKUP TOOL gestdeveloppeurs_db
mysqldump -u root -h localhost  gestdeveloppeurs_db --routines > gestdeveloppeurs_db.sql 
@PAUSE