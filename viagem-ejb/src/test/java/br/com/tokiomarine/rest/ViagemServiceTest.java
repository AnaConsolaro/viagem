package br.com.tokiomarine.rest;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.tokiomarine.controller.ViagemFacade;
import br.com.tokiomarine.exception.ViagemException;
import br.com.tokiomarine.model.Gasto;
import br.com.tokiomarine.model.TipoGasto;
import br.com.tokiomarine.model.Viagem;

@RunWith(EasyMockRunner.class)
public class ViagemServiceTest {

	@TestSubject
	private ViagemService service = new ViagemService();

	@Mock
	private ViagemFacade mockViagemFacade;

	@Test
	public void deveriaRetornarTotaldeGastoZeroTest() throws Exception {
		Viagem viagem = new Viagem();
		viagem.setGastos(new HashSet<Gasto>());
		expect(mockViagemFacade.buscarPorId(1L)).andReturn(viagem);

		replay(mockViagemFacade);

		GastosVO gastosVO = service.listarGastosViagem(1L, null);

		verify(mockViagemFacade);

		assertNotNull(gastosVO);
		assertTrue(gastosVO.getListaGastos().isEmpty());
		assertEquals(0.0, gastosVO.getTotalGastos(), 0.0);
	}

	@Test
	public void test2() throws Exception {
		Viagem viagem = new Viagem();
		viagem.setGastos(new HashSet<Gasto>());
		expect(mockViagemFacade.buscarPorId(1L)).andReturn(viagem);

		replay(mockViagemFacade);

		GastosVO gastosVO = service.listarGastosViagem(1L, "");

		verify(mockViagemFacade);

		assertNotNull(gastosVO);
		assertTrue(gastosVO.getListaGastos().isEmpty());
		assertEquals(0.0, gastosVO.getTotalGastos(), 0.0);
	}

	@Test
	public void test3() throws Exception {
		Viagem viagem = new Viagem();
		viagem.setGastos(new HashSet<Gasto>());
		expect(mockViagemFacade.buscarPorId(1L)).andReturn(viagem);

		replay(mockViagemFacade);

		try {
			service.listarGastosViagem(1L, "TESTE");
			fail(); 
		} catch (ViagemException e) {
			assertEquals("Tipo de Gasto Não Existente", e.getMessage());
		}

		verify(mockViagemFacade);

	}
	
	@Test
	public void test4() throws Exception {
		Viagem viagem = new Viagem();
		viagem.setGastos(new HashSet<Gasto>());
		Gasto gasto = new Gasto();
		gasto.setTipo(TipoGasto.HOTEL);
		gasto.setValor(100.0);
		gasto.setEfetivado(true);
		viagem.getGastos().add(gasto );
		
		expect(mockViagemFacade.buscarPorId(1L)).andReturn(viagem);

		replay(mockViagemFacade);

		GastosVO gastosVO = service.listarGastosViagem(1L, "HOTEL");

		verify(mockViagemFacade);

		assertNotNull(gastosVO);
		assertTrue(gastosVO.getListaGastos().size() == 1);
		assertEquals(100.0, gastosVO.getTotalGastos(), 0.0);
	}

}
