package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistent {

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


            //비영속 상태
            Member member = new Member();
            member.setId(3L);
            member.setName("비영속");

            //영속 상태 - 저장 x
            System.out.println("--- BEFORE ---");
            em.persist(member);//1차 캐쉬에 저장 -> 조회
            //준영속
            em.detach(member);
            //삭제
            em.remove(member);
            System.out.println("--- AFTER ---");

            tx.commit(); //이 시점에서 커밋됨
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
