import java.awt.event.WindowFocusListener;
import java.util.Scanner;


public class main {
    public static String[] todos = new String[2];
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showToDoList() {
        System.out.println("To Do List : ");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todos[i] != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }
    public static void addToDoList(String todo) {
        resizeArrayIfFull();

        for (int i = 0; i < todos.length; i++){
            if(todos[i] == null){
                todos[i] = todo;
                break;
            }
        }
    }

    private static void resizeArrayIfFull() {
        boolean isFull = isArrayFull();

        if(isFull){
            resizeArrayToTwoTimesBigger();
        }
    }

    private static void resizeArrayToTwoTimesBigger() {
            String[] temp = todos;
            todos = new String[todos.length * 2];
            for( int i = 0; i< temp.length; i++){
                todos[i] = temp[i];
            }
        }


    private static boolean isArrayFull() {
        boolean isFull = true;
        for(int i = 0; i < todos.length; i++){
            if(todos[i] == null){
                isFull = false;
                break;
            }
        }
        return isFull;
    }
    public static boolean removeToDoList (Integer number){
        if (isSelectedTodoNotValid(number)) return false;

        for(int i = number - 1; i < todos.length; i++){
            //if todo is the last element
            if( i == (todos.length) - 1){
                todos[i] = null;
            } else{
                //replace with the element on the right
                todos[i] = todos [i + 1];
            }
        }
        return  true;
    }

    private static boolean isSelectedTodoNotValid(Integer number) {
        if(number <= 0){
            return true;
        }

        if(number - 1 > todos.length - 1){
            return true;
        }

        if(todos[number - 1] == null){
            return true;
        }
        return false;
    }
    public static boolean editTodolist(Integer number, String newTodo){
        if(isSelectedTodoNotValid(number)){
            return false;
        }
        todos[number-1] = newTodo;
        return  true;
    }

    public static void showMainMenu (){
        boolean isRunning = true;
        while(isRunning){
            showToDoList();
            System.out.println("Menu : ");
            System.out.println("1.Tambah ");
            System.out.println("2.Hapus ");
            System.out.println("3.Edit ");
            System.out.println("4.Keluar ");
            String selectedMenu = input.nextLine();

            switch (selectedMenu){
                case "1" :
                    showMenuAddTodoList();
                    System.out.println("Menu add todo list");
                    break;
                case "2" :
                    System.out.println("Menu remove todo list");
                    showRemoveTodoList();
                    break;

                case "3" :
                    System.out.println("Menu edit todo list");

                    break;

                case "4" :
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihannya Salah");
            }
        }
    }
    public static void  showMenuAddTodoList(){
        System.out.println("Menambah todo list");
        String todo = input("todo (x jika batal)");
        if(todo.equals("x")){
            //batal
        }else{
            addToDoList(todo);
        }
    }
    public static void showRemoveTodoList(){
        System.out.println("Menghapus todo list");
        String todoYangDipilih =  input("nomor todo yang dihapus (x jika batal)");
        if(todoYangDipilih.equals("x")){
            //batal;
        } else{
            boolean success = removeToDoList(Integer.valueOf(todoYangDipilih));
            if(!success){
                System.out.println("Gagal menghapus todo list");
            }
        }
    }

    public static void  showMenuEditTodoList(){
        System.out.println("Mengedit todo list");
        String selectedTodo = input("Masukin nomor todo (x jika batal)");
        if(selectedTodo.equals("x")){
            return;
        }
        String newTodo = input("Masukin todo yang baru (x jika batal)");
        if(newTodo.equals("x")){
            return;
        }
        boolean isEditTodoSuccess = editTodolist(Integer.valueOf(selectedTodo), newTodo);
        if(isEditTodoSuccess){
            System.out.println("Behasil mengedit todo");
        } else{
            System.out.println("Gagal mengedit todo");
        }
    }
}