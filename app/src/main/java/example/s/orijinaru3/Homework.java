package example.s.orijinaru3;

import java.io.Serializable;

/**
 * Created by s on 2016/12/16.
 */
public class Homework implements Serializable {
    private static final long serialVerdionUID = 625575224851301907L;
    private String subject;
    private String content;
    // private String deadline;
    private long diffday;


    public Homework(String subject, String content, long diffday) {
        this.subject = subject;
        this.content = content;
        //  this.deadline = deadline;
        this.diffday = diffday;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public String getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(String deadline) {
//        this.deadline = deadline;
//    }

    public long getDiffday() {
        return diffday;
    }

    public void setDiffday(long diffday) {
        this.diffday = diffday;
    }
}
