#!/bin/bash

# Electronics Database Installation Script
# ========================================
# PostgreSQL 9.4 on Linux/Mac

# Setup Instructions
# ------------------
#
# Open a terminal, then run the following commands:
#
# vi ~/.pgpass
#
# Add the following 2 lines to .pgpass (Without the first # on each line)
#    #hostname:port:database:username:password
#    localhost:5432:electronics:intuit:Intuit!2d7D2f3B
#
# Save and close .pgpass
#
# chmod 600 ~/.pgpass
#
# Close and re-open the terminal
#
# cd ~/repos/electronics-db-services/src/main/resources/database/shell-scripts
#
# ./create-database.sh
#


# Variables
# ------------------------------------------------------------------------------
server=localhost
database=electronics
port=5432
dbuser=intuit

OS=`uname -s`
SUDO=`which sudo`
PSQL=`which psql`
ADMIN_DB="postgres"
ADMIN_ROLE="postgres"

if [ "$OS" == "Darwin" ] ; then
    ADMIN_DB="template1"
    ADMIN_ROLE=`whoami`
fi

# Create the intuit user role (use sudo)
# ------------------------------------------------------------------------------
echo "=== Creating role (user) ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $ADMIN_DB < ../create-role.sql

# Drop and Create the DB as postgres (use sudo)
# ------------------------------------------------------------------------------
echo "=== Dropping old DB (if exists) ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $ADMIN_DB < ../drop-database.sql
echo "=== Creating new DB ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $ADMIN_DB < ../create-database.sql

# Create tables
# ------------------------------------------------------------------------------
echo "=== Creating tables ==="
echo "- category"
$PSQL -h localhost -p 5432 -U intuit -w -d $database  < ../tables/category.sql
echo "- product"
$PSQL -h localhost -p 5432 -U intuit -w -d $database  < ../tables/product.sql

# Grant role permissions
# ------------------------------------------------------------------------------
echo "=== Granting role permissions ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $database < ../grant-role-permissions.sql

# Default data
# ------------------------------------------------------------------------------

# echo "=== Inserting default data ==="
# echo "- category"
# $PSQL -h localhost -p 5432 -U intuit -w -d $database  < ../data/category.sql
# echo "- product"
# $PSQL -h localhost -p 5432 -U intuit -w -d $database  < ../data/product.sql
