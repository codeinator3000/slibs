package com.slibs.slibs.schedulers;

/**
 * Интерфейс всех планировщиков
 */
public interface Scheduler {
    /**
     * Запускает работу планировщика.
     * @return true, если получилось запустить планировщик, иначе false.
     */
    boolean start();

    /**
     * Останавливает работу планировщика.
     * @return true, если получилось остановить планировщик, иначе false.
     */
    boolean end();

    /**
     * Возвращает текущее состояние планировщика.
     * @return true, если запущен; false, если остановлен.
     */
    boolean getActiveStatus();
}
