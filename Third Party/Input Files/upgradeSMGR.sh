#! /bin/bash
###############################################################################
##
##      Copyright (c) 2014 Avaya Inc All Rights Reserved
##      THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF AVAYA INC
##
##      The copyright notice above does not evidence any
##      actual or intended publication of such source code.
##
###############################################################################

help()
{
        echo -e "Usage:  upgradeSMGR <Absolute Path Of Data Migration> [OPTION]...\n"
	echo -e "This script would upgrade your System Manager. Upgrade Utility can be used with below mention options. \n\n"
        echo -e "-m, --migrate		- Perform data migration on Avaya Aura(R) System Manager"
        echo -e "-V, --validate		- Perform system validation. Can be used in conjunction with --migrate"
        echo -e "-v, --verbose		- Toggle verbose mode. Default is off for migration and on for validation"
        echo -e "-H, --history		- Print data migration history"
        echo -e "-f, --filepath		- Provide File Path to Data Migration"
        echo -e "    --version		- Print program version & exit\n"
	echo -e "Example:\n"
	echo -e "	upgradeSMGR /home/admin/datamigration.bin -m "
	echo -e "	upgradeSMGR /home/admin/datamigration.bin -m -v\n\n"
	
        echo -e "Contact Avaya Support for further assistance.\n"
exit 1
}

getArg(){

arg_c=$#
count=1
val_arra=($@)
dm_arg=""
zipPath=""
#zipPath=$4
# argc_new=$#
# if [ "$arg_c" == 5 ]; then
		# echo "Args are $arg_c"
		# argc_new = 4
	# else
		# echo "Args are $arg_c"
		# argc_new = $arg_c
# fi

while [ $count -lt $arg_c ]
do
   dm_arg="$dm_arg ${val_arra[count]}"
   current_arg="${val_arra[count]}"

	case $current_arg in
			-m)                     shift;;
			--migrate)              shift;;
			-V)                     shift;;
			--validate)             shift;;
			-b)			
			#zipPath="$5"			
			shift;;
			--backupPath)	
			#zipPath="$5"		
			shift;;
			-v)                     shift;;
			--verbose)              shift;;
			-H)                     shift;;
			--history)              shift;;
			 -f)                    shift;;
			--filepath)             shift;;
			--version)              shift;;
			-s)                     ;;
			*)              echo -e "\n"
							echo "Invalid option used $current_arg"
							echo -e "\n"
							help
							exit;;
	esac
	shift
	
	if [ $current_arg == -b ]; then
		count=`expr $count + 2`
		echo count
	else
		count=`expr $count + 1`
		echo count
	fi
done
}

echo zipPath
echo $zipPath
echo "$zipPath"
echo "Arg:"
echo $5

checkDM()
{
DOS2UNIX=/usr/bin/dos2unix
  DataMig=$1
  ARCHIVE_START=$(awk '/^__START_OF_ARCHIVE__/{print NR+1; exit 0;}' $DataMig)
if [ ! -z "$ARCHIVE_START" ];then
 pa=/tmp/$$
 mkdir -p $pa;cd $pa
 tail -n+$ARCHIVE_START $DataMig > $$.zip 
 unzip $$.zip "Datamigartion_rel.txt" >> /dev/null 2>&1
 [ $? -ne 0 ] && echo "ERROR: Data Migration Binary got corrupted." && rm -rf $pa && exit 1;
$DOS2UNIX -q $pa/Datamigartion_rel.txt
 env_txt=$pa/Datamigartion_rel.txt
 product_id=`grep "^ProductID=" $env_txt | awk -F= '{print $2}'`
 [ ! $product_id = "smgr" ] && echo "ERROR: This system is not valid System Manager." && rm -rf $pa && exit 1;
 rm -rf $pa
else
 echo "ERROR: Please execute valid System Manager System."
 exit 1
fi
}

# Main fun

dm_arg=$@

if [ "$#" -gt 0 ];then

   if [ ! -f $1 ]; then
     echo -e "\n\nINFO: Incorrect file!!! Please provide the correct file name,absolute path and required options to the Data Migration Utility.\n"
        help
     exit 1
   fi

   # if [ ! -f $5 ]; then
     # echo -e "\n\nINFO: Incorrect file!!! Please provide the correct file name,absolute path and required options to the ZIP File.\n"
        # help
     # exit 1
   # fi
   
  if [[ "$1" == /* ]];then
    UTIL=$1
  else
    echo -e "\n\nINFO: Incorrect Path!!! Please provide the absolute path and required options to the Data Migration Utility.\n"
    help
    exit 1
  fi
  
  

   if [[ -z $2 && -z $3 ]]; then
      echo -e "\n\nINFO: Options missing!!! Please provide the required options to the data migration utility. Please refer to the below mentioned Usage \n"
        help
     exit 1
   fi
   
	if [[ -z $4 ]]; then
		echo -e "\n\nINFO: You have not choosen backupfilepath as CLI argument. \n"
        #help
		#exit 1
	else
		if [[ -z $5 ]]; then
		echo -e "\n\nINFO: You have not given backupfilepath as CLI argument. Please give zip file path \n"
        help
		#read zipPath
		# help
		exit 1
		
		else
			if [[ "$5" == /* ]];then
			# zipPath=$5
					echo "Fifth argument is given:"
					echo zipPath
					echo $5
			else
					echo zipPath
					echo "Fifth argument is not given:"
					echo "falsified"
					echo -e "\n\nINFO: Incorrect Path!!! Please provide the absolute path and required options to the Data Migration Utility.\n"
					help
					exit 1
			fi
		# zipPath="$5"
		 # echo -e "\n\nINFO: Choosen Path!!! Zip file path\n"
		 # echo zipPath
		 # echo "FP"
		 # echo $5
		# # help
		# exit 1
		fi
	fi
	
	
	
else
 echo -e "INFO: Incomplete Command!!! Please provide the correct absolute path and required options to the Data Migration Utility: \c"
 read ANS
 dm_arg=$ANS
 ANS=`echo $ANS | awk '{print $1}'`
  if [[ "$ANS" == /* ]];then
      UTIL=$ANS
    else
            echo -e "ERROR: You have not provided correct absolute path and required options to the Data Migration Utility. Please refer to the below mentioned Usage.\n"
            help
            exit 1
   fi

   if [[ -z $2 && -z $3 ]]; then
      echo -e "\n\nINFO: Options missing!!! Please provide the required options to the data migration utility. Please refer to the below mentioned Usage. \n"
        help
     exit 1
   fi
   
   # if [[ "$2" == /* ]];then
      # zipPath=$2
	  # echo zipPath
    # else
            # echo -e "ERROR: You have not provided correct zip file. Please refer to the below mentioned Usage.\n"
            # help
            # exit 1
   # fi

 if [ \( ! -f "$ANS" \) -o \( -z "$ANS" \) ];then
     echo -e "ERROR: You have not provided correct absolute path and required options to the Data Migration Utility, hence please refer to the below mentioned Usage.\n"
        help
        exit 1

 fi
  UTIL=$ANS
  
fi        

. /etc/profile

chmod +x $UTIL
dir_name=`dirname $UTIL`
file_name=`basename $UTIL`
checkDM $UTIL

echo "ZIP Path: "
echo zipPath

getArg $dm_arg
if [ -z "$dm_arg" ];then
  help
 exit
fi

cd $dir_name
#./$file_name  | tee -a /var/log/Avaya/upgradeSMGR.$$

if [ -f ${SMGR_INSTALL_DIR}/vsp/common_services/backupFile.txt ]; then
	export BACKUP_FILE=`cat ${SMGR_INSTALL_DIR}/vsp/common_services/backupFile.txt`
fi

if [[ -f $5 ]]; then
	export BACKUP_FILE=$5
fi

if [ -f ${SMGR_INSTALL_DIR}/vsp/common_services/patchFile.txt ]; then
	export PATCH_FILE=`cat ${SMGR_INSTALL_DIR}/vsp/common_services/patchFile.txt`
fi

./$file_name $dm_arg | tee -a /var/log/Avaya/upgradeSMGR.$$

