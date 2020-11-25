#!/bin/bash

script_file=$(readlink -f $0)
script_dir=$(dirname ${script_file})

${script_dir}/stop_bus_server.sh
${script_dir}/start_bus_server.sh