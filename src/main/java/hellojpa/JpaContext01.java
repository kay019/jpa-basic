package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaContext01 {

    public static void main(String[] args){

        //일괄적인 한 단위 작업마다 생성해야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션 호출
        //jpa 모든 작업은 트랜잭션 안에서 작업
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 정석
        try{
            //code

            // 1차 캐쉬에 있어서 1번을 뒤짐. 현업에서 엄청난 도움은 되진 않음.

            //컨셉에 대한 이해, 객체 지향적인 코딩을 위한 이해
            Member findMember1 = em.find(Member.class, 4L);
            Member findMember2 = em.find(Member.class, 4L);

            //영속 엔티티의 동일성 보장
            //jpa가 보장해줌 - 1차 캐쉬가 있어서 가능
            System.out.println(findMember1 == findMember2);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
