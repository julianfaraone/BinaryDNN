
SBT ?= sbt
SBT_FLAGS ?= -Dsbt.log.noformat=true
CC = gcc

CHISEL_FLAGS := --wio

top_srcdir ?= .
srcdir ?= src/main/scala/
csrrcdir ?= src/main/C
top_file := src/main/scala/top.scala
source_files := $(wildcard $(srcdir)/*/*.scala) $(wildcard $(srcdir)/*/*/*.scala) $(top_file)
executables := $(filter-out top, $(notdir $(basename $(source_files))))

# Handle staging.
staging_dir := ~/.sbt/0.13/staging
staging_dirs := $(foreach dir, $(wildcard $(staging_dir)/*), $(wildcard $(dir)/*)) # Get the directory of each staging project.
staging_targets := $(addsuffix /update.stage, $(staging_dirs)) # Add a phoney target to staging dir.

default: verilog

all: check verilog

clean:
	-rm -f *.h *.hex *.flo *.cpp *.o *.out *.v *.vcd $(executables)
	sbt clean
	-rm -rf project/target/ target/

cleanall: clean
	-rm -rf $(staging_dir)/*

verilog: MyTopLevelModule.v

MyTopLevelModule.v: $(source_files)
	echo "$(source_files)"
	set -e pipefail; $(SBT) $(SBT_FLAGS) "run --genHarness --backend v $(CHISEL_FLAGS)"

check test:
	$(SBT) $(SBT_FLAGS) test

%.stage:
	cd $(@D); git pull

download: $(staging_targets)

.PHONY: all check clean cleanall verilog download
