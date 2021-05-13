class Main{
        static String doc = "";
        static String buffer = "";
        interface Command{
                void execute();
        }

        static class Save implements Command{
                String string;
                Save(String str){
                        this.string = str;
                        execute();
                }
                @Override
                public void execute(){
                        doc = "" + string;
                }
        }

        static class Copy implements Command{
                int n,m;
                void cut(int n, int m){
                        execute();
                        String string = "";
                        for(int i = 0; i < doc.length(); i++){
                                if(i<n || i>=m)
                                        string+= doc.charAt(i);
                        }
                        doc = "" + string;      
                }
                Copy(int n, int m){
                        this.n = n;
                        this.m = m;
                        execute();
                }
                @Override
                public void execute(){
                        String string = "";
                        for(int i = n; i < m; i++)
                                string += doc.charAt(i);
                        buffer = ""+string;
                }
        }
static class Paste implements Command{
                
                int n;
                boolean undone = true;
                Paste(int n){
                        this.n = n;
                        execute();
                }
                @Override
                public void execute(){
                        int i = 0;
                        String string = "";
                        while(i < doc.length()){
                                if(i==n)
                                        string += buffer;
                                string +=doc.charAt(i);
                                i++;
                        }
                        doc = ""+string;
                }
                
                void undo(){
                        if(undone){
                                String string = "";
                                int count = buffer.length();
                                for(int i = 0 ; i < doc.length(); i++){
                                        if(i>=n && count!=0)
                                                count--;
                                        
                                        else
                                                string+=doc.charAt(i);
                                        
                                }
                                doc = ""+string;
                                
                        }
                        else
                                execute();
                        this.undone = !undone;
                }
        }
        public static void main(String [] args){
                new Save("abcdefgh");
                System.out.println("After Save operation:\ndoc = "+doc);
                Copy copy = new Copy(3,5);
                copy.cut(3,5);
                System.out.println("\nAfter copy and cut:\ndoc = "+doc+" and buffer = "+buffer);
                Paste paste = new Paste(0);
                System.out.println("\nAfter paste:\ndoc = "+doc+" and buffer = "+buffer);
                paste.undo();
                System.out.println("\nAfter undo()\ndoc = "+doc+" and buffer = "+buffer);
                paste.undo();
                System.out.println("\nUndo again:\ndoc = "+doc+" and buffer = "+buffer);
        }
}
