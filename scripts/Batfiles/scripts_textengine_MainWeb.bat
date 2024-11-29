@echo off
<<<<<<< HEAD
cd C:\javaproject\testTextEngine\TextEngine/scripts
=======
cd C:\github\TextEngine/scripts
>>>>>>> origin/v1.5.1
echo compilation en cours
call ant -DUSER_DIR=. build
call ant -DNOM_CLASS_MAIN=scripts_textengine.MainWeb run