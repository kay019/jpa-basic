package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaSemiPersistent {

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

            //준영속

            //영속 상태
            Member member = em.find(Member.class, 10L);
            member.setName("AAAA"); // <- 더티 체킹

            //jpa - 관리 안함
            //em.detach(member); // 웹 개발 시 복잡할 때
            em.clear(); //영속성 컨텐츠 전체 초기화
            //테스트 케이스 작성시 눈으로 확인할 때 도움됨
            System.out.println("=======");
            Member member2 = em.find(Member.class, 10L);

            System.out.println("=======");

            //특정 상태 준영속 전환 - > detach


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
