#!/bin/bash

Xephyr -no-host-grab :5 &

sleep 1

export DISPLAY=:5


echo After you click OK, notion will load and a few windows.  Make sure that the Java DragAndDrop Example application is in the right panel, and everything else is in the left panel.  When you attempt to drag, you should get a cross icon. | fmt -w 50 | xmessage -file -

notion &

sleep 1


#xclock &

java ./DragAndDropExample.java &

xterm
