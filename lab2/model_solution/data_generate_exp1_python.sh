STATES="sorted reverse none"
SIZES="10000 20000 30000 40000 50000"

rm data/sorted_data_python.dat
rm data/reverse_data_python.dat
rm data/none_data_python.dat
rm data/sorted_data_python.csv
rm data/reverse_data_python.csv
rm data/none_data_python.cvs

for STATE in $STATES
do

for SIZE in $SIZES
do

for COUNT in 1 2 3 4 5
do

    # Debugging statement to check program calls working as expected
    python3 ../../search_and_sort_lab/python/speller_darray.py -d dictionaries_and_queries/dict_${SIZE}_${STATE}_$COUNT -m 1 -s 100000 dictionaries_and_queries/single_query

    ALL_TIME=`(time -p python3 ../../search_and_sort_lab/python/speller_darray.py -d dictionaries_and_queries/dict_${SIZE}_${STATE}_$COUNT -m 1 -s 100000 dictionaries_and_queries/single_query) 2>&1 | grep -E "user|sys" | sed s/[a-z]//g`
    
    RUNTIME=0
    for i in $ALL_TIME;
    do RUNTIME=`echo $RUNTIME + $i|bc`;
    done
    echo $SIZE $RUNTIME >> data/${STATE}_data_python.dat
    echo $SIZE, $RUNTIME >> data/${STATE}_data_python.csv
    
done

done

done
