package br.com.tokiomarine.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.tokiomarine.exception.ViagemException;
import br.com.tokiomarine.model.Viagem;

@RunWith(EasyMockRunner.class)
public class ViagemFacadeImplTest {
	
	@TestSubject
	private ViagemFacadeImpl facade = new ViagemFacadeImpl();

	@Mock
	private EntityManager mockEntityManager;
	
	@Before
	public void setup() {
		reset(mockEntityManager);
	}

	@Test
	public void test() {
		expect(mockEntityManager.find(Viagem.class, 1l)).andReturn(null);
		
		replay(mockEntityManager);
		
		Viagem viagem = facade.buscarPorId(1l);
		
		verify(mockEntityManager);
		
		assertNull(viagem);
	}

	@Test(expected = IllegalStateException.class)
	public void test2() {
		expect(mockEntityManager.find(Viagem.class, 1l)).andThrow(new IllegalStateException());
		
		replay(mockEntityManager);
		
		Viagem viagem = facade.buscarPorId(1l);
		
		verify(mockEntityManager);
		
		assertNull(viagem);
	}
	
	@Test
	public void test3() {
		expect(mockEntityManager.find(Viagem.class, 10l)).andReturn(null);
		
		replay(mockEntityManager);
		
		Viagem viagem = null;
		try {
			viagem = facade.atualizar(10l, new Viagem() );
		} catch (ViagemException e) {
			assertEquals("Id Inexistente", e.getMessage());
		}
		
		verify(mockEntityManager);
		
		assertNull(viagem);
	}
	
	@Test
	public void test4() throws ViagemException {
		Viagem viagemAtualizar = new Viagem();
		expect(mockEntityManager.find(Viagem.class, 10l)).andReturn(viagemAtualizar);
		expect(mockEntityManager.merge(viagemAtualizar)).andReturn(viagemAtualizar);
		
		replay(mockEntityManager);
		
		Viagem viagem = facade.atualizar(10l, viagemAtualizar);
		
		verify(mockEntityManager);
		
		assertNotNull(viagem);
	}
}
