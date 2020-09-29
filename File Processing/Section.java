package filesprocessing;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
/**
 * section class contains the filter and order for the section
 * */
public class Section {
    public Filter filter;
    public Order comparator;
    public File[]files;

    Section(Filter filter,Order comparator, File[]files){
        this.filter=filter;
        this.comparator=comparator;
        this.files=files;
    }
    /**
     * this function return the filtered files
     * **/
    private List<File>fileFiltering(List<File>filesList,Filter filter){
        if (filter.getFilter()==null){
            return filesList;
        }
        return filesList.stream().filter(filter.getFilter()).collect(Collectors.toList());
    }
    /**
     * sort the files in the given order by using quicksort
     * **/
    public void quickSort(File[]files,int low,int high){
        if (low<high){
            int p=partition(files,low,high);
            quickSort(files,low,p-1);
            quickSort(files,p+1,high);
        }
    }
    /**
     * helper function for quicksort
     * */
    public int partition(File[] files ,int low,int high){
        Random random=new Random();
        int numPivot=random.nextInt((high-low)+1)+low;
        swap(files,numPivot,high);
        File pivot=files[high];
        int i=low-1;
        for (int j =low;j<high;j++){
            if (comparator.getOrder().compare(files[j],pivot)<0){
                i++;
                swap(files,i,j);
            }
        }
        swap(files,i+1,high);
        return i+1;

    }
    /**
     * helper function for quicksort
     * */
    private void  swap(File[]files,int i,int j){
        File tempFile=files[i];
        files[i]=files[j];
        files[j]=tempFile;
    }
    /**
     * returns the ordered and filtered files
     * **/
    public File[] filterAndOrder(){
        List<File>filterdList=fileFiltering(Arrays.asList(files),filter);
        File[]filterdarr=filterdList.toArray(new File[filterdList.size()]);
        quickSort(filterdarr,0,filterdarr.length-1);
        return filterdarr;
    }

}
