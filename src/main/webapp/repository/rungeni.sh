#!/bin/bash
export HOME="/etc/teapot/wrappers"
echo "Geni File  : " $1
echo "Save to    : " $2
echo "Source File: " $3
echo "Parameters : " $4
sage -c 'load("'"$1"'");geni("'"$2"'","'"$3"'","'"$4"'")'
