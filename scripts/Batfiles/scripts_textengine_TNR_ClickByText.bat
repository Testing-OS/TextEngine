@echo off
cd C:\github\TextEngine/scripts
echo compilation en cours
call ant -DUSER_DIR=. build
call ant -DNOM_CLASS_MAIN=scripts_textengine.TNR.ClickByText run