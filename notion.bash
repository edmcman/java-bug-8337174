#!/bin/bash

Xephyr -no-host-grab :5 &

sleep 1

export DISPLAY=:5

xterm &

xclock &

notion &

java ./DragAndDropExample.java

#sleep 1

#xdotool movemouse 374 30

#sleep 1

#xdotool mousedown 1

#killall Xephyr
