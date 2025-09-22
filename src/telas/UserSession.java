package telas;

public class UserSession {
    public static int idFuncionario = -1;
    public static String permissoes = "";

    public static boolean isGerente() {
        return "gerente".equalsIgnoreCase(permissoes);
    }
}