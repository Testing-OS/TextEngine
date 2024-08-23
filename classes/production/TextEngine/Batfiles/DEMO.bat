@echo off
setlocal enabledelayedexpansion

rem --------------------------------------------à completer----------------------------------------------------------------------------------------------------
set "chemin="
set "NomInstance="
set "Token="
set "TestPlanName="
set "URL="
rem -------------------------------------------------------------------------------------------------------------------------------------------------------------

set "BrowserStackSDK=%chemin%\runners\BrowserStack\browserstack-java-sdk-1.13.3.jar"

set "search_NomInstance=public static String NomInstance="
set "search_Token=public static String Token="
set "search_TestPlanName=public static String TestPlanName="
set "search_URL=public static String URL="
set "search_BrowserStackSDK=public static String BrowserStackSDK="

set "newLine_NomInstance=    public static String NomInstance="%NomInstance%";"
set "newLine_Token=    public static String Token="%Token%";"
set "newLine_TestPlanName=    public static String TestPlanName="%TestPlanName%";"
set "newLine_URL=    public static String URL="%URL%";"
set "newLine_BrowserStackSDK=    public static String BrowserStackSDK="%BrowserStackSDK%";"

set "file=%chemin%\runners\BrowserStack\BrowserStackLauncher.java"
set "tempfile=%chemin%\runners\BrowserStack\tempfile.java"

(for /f "delims=" %%i in ('type "%file%"') do (
    set "line=%%i"
    if "!NomInstance!" neq "" (
        if "!line:%search_NomInstance%=!" neq "!line!" (
            echo !newLine_NomInstance!
        ) else if "!Token!" neq "" (
            if "!line:%search_Token%=!" neq "!line!" (
                echo !newLine_Token!
            ) else if "!TestPlanName!" neq "" (
                if "!line:%search_TestPlanName%=!" neq "!line!" (
                    echo !newLine_TestPlanName!
                ) else if "!URL!" neq "" (
                    if "!line:%search_URL%=!" neq "!line!" (
                        echo !newLine_URL!
                    ) else if "!BrowserStackSDK!" neq "" (
                        if "!line:%search_BrowserStackSDK%=!" neq "!line!" (
                            echo !newLine_BrowserStackSDK!
                        ) else (
                            echo !line!
                        )
                    ) else (
                        echo !line!
                    )
                ) else (
                    echo !line!
                )
            ) else (
                echo !line!
            )
        ) else (
            echo !line!
        )
    ) else (
        echo !line!
    )
)) > "%tempfile%"

del "%file%"
ren "%tempfile%" "BrowserStackLauncher.java"

cd "%chemin%"
echo compilation en cours
call ant -DUSER_DIR=. build
call ant -DNOM_CLASS_MAIN=runners.BrowserStack.BrowserStackLauncher run
call ant -DUSER_DIR=. build
call ant -DNOM_CLASS_MAIN=runners.BrowserStack.PlanTestBrowserStack -DJAVA_AGENT=-javaagent:%BrowserStackSDK% run




