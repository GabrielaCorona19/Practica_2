package condicionescompetencias;
import javax.swing.JTextArea;
public class Hilo extends Thread {
    private JTextArea area; 
    private RCompartido rc;
    private boolean suspender;
    private boolean pausar;
    Hilo(JTextArea area, RCompartido rc){
        this.area=area;
        this.rc=rc;
        suspender=false;
        pausar=false;
    }
    
    public void run(){
        while(true){
            try{
                rc.setDatoCompartido(this.getName());
                area.append(rc.getDatoCompartido()+"\n");
                sleep(1600);
            }catch(Exception e){e.printStackTrace();}
        }
    }
        //Pausar el hilo
    synchronized void pausarhilo(){
        pausar=true;
        //lo siguiente garantiza que un hilo suspendido puede detenerse.
        suspender=false;
        notify();
    }

    //Suspender un hilo
    synchronized void suspenderhilo(){
        suspender=true;
    }

    //Renaudar un hilo
    synchronized void renaudarhilo(){
        suspender=false;
        notify();
    }
    
}
