package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTransacao;

public class Main {

	public static void main(String[] args) {

		int calculoMax = 0;
		int calculoMin = 0;
		int tempoTransacao = 0;
		int repete = 0;

		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);

		for (int idThread = 1; idThread <= 21; idThread++) {
			if (idThread % 3 == 1) {
				repete = 2;
				calculoMax = 801;
				calculoMin = 200;
				tempoTransacao = 1000;
			}
			if (idThread % 3 == 2) {
				repete = 3;
				calculoMax = 1001;
				calculoMin = 500;
				tempoTransacao = 1500;
			}
			if (idThread % 3 == 0) {
				repete = 3;
				calculoMax = 1001;
				calculoMin = 1000;
				tempoTransacao = 1500;
			}
			Thread tTransacao = new ThreadTransacao(idThread, calculoMax, calculoMin, tempoTransacao, repete, semaforo);
			tTransacao.start();
		}
	}

}