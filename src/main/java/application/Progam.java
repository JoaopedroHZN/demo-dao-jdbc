package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Progam {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1. Injetando a dependência usando a Fábrica
        // O tipo é a Interface (SellerDao), mas por trás roda a SellerDaoJDBC!
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller insert ===");

        // 2. Criamos o departamento correspondente (Certifica-te que o ID 1 existe no teu banco)
        Department department = new Department(1, "Computers");

        // 3. Criamos o novo vendedor com TODOS os dados preenchidos (repara no 'new Date()')
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);

        // 4. Chamamos o método insert que escreveste no SellerDaoJDBC
        sellerDao.insert(newSeller);

        // 5. Se correr tudo bem, o MySQL gera o ID e o Java mostra aqui:
        System.out.println("Inserido com sucesso! Novo Id = " + newSeller.getId());

        System.out.println("\n=== TEST 2: seller update ===");

        // 1. Buscamos um vendedor que já existe no banco (ex: o vendedor com ID 1)
        // O sellerDao vai lá no MySQL, pega os dados e joga dentro da variável 'seller'
        Seller seller = sellerDao.findById(1);

        // 2. Alteramos os dados que a gente quiser usando os SETs
        seller.setName("Miguel Montelo");
        seller.setEmail("miguel@gmail.com");
        seller.setBaseSalary(6500.0);
        // Se quiser mudar o departamento dele também, é só descomentar a linha abaixo:
        // seller.setDepartment(new Department(2, "Electronics"));

        // 3. Chamamos o método update passando o objeto já modificado
        sellerDao.update(seller);

        // 4. Mensagem de sucesso
        System.out.println("Update realizado com sucesso!");


        System.out.println("Teste Seller Delete");
        System.out.println("Enter");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed");
        sc.close();


    }
}