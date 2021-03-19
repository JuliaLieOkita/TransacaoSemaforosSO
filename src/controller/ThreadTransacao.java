package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {

	private int idThread;
	private int calculoMax;
	private int calculoMin;
	private int tempoTransacao;
	private int repete;
	private Semaphore semaforo;

	public ThreadTransacao(int idThread, int calculoMax, int calculoMin, int tempoTransacao, int repete,
			Semaphore semaforo) {
		this.idThread = idThread;
		this.calculoMax = calculoMax;
		this.calculoMin = calculoMin;
		this.tempoTransacao = tempoTransacao;
		this.repete = repete;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		for (int i = 0; i < repete; i++) {
			calculo();
			// ----------Início Seção Crítica----------
			try {
				semaforo.acquire();
				transacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
				// ----------Fim Seção Crítica----------
			}
		}
	}

	private void calculo() {
		int tempoCalculo = (int) ((Math.random() * calculoMax) + calculoMin);
		try {
			sleep(tempoCalculo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #" + idThread + " Processando...");
	}

	private void transacao() {
		try {
			sleep(tempoTransacao);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #" + idThread + " Transação BD...");
	}

}