package in.filecollection;

import java.util.List;

/**
 * Created by plank-arfaa on 9/4/2015.
 */
public class FileAccessTime {
    private FileCollection fcollection;
    public FileCollection getFcollection() {
        return fcollection;
    }
    public void setFcollection(FileCollection fcollection) {
        this.fcollection = fcollection;
    }
    public List ViewAccessTime(){
        List FileList = this.fcollection.getFileList();
        return FileList;
    }
}
