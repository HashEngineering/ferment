rm ./src/main/java/org/dash/sdk/*.java
swig -java -outdir src/main/java/org/dash/sdk -package org.dash.sdk -o src/main/c/sdk.c example.i