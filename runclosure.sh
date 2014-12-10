#!/bin/bash
chmod +x src/main/webapp/bower_components/closure-libraray/closure/bin/build/depswriter.py
src/main/webapp/bower_components/closure-libraray/closure/bin/build/depswriter.py --root_with_prefix="src/main/webapp/app ../../../../app" --output_file=src/main/webapp/deps.js
