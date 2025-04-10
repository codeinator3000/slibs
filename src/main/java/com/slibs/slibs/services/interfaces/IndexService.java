package com.slibs.slibs.services.interfaces;

public interface IndexService {
    /**
     * Обновляет данные о всех библиотеках в основной и поисковой базах данных.
     * @return true, если обновление прошло успешно
     */
    public Boolean updateIndex();
    /**
     * Возвращает состояние автоматического обновления.
     * @return true, если автоматическое обновление включено
     */
    public Boolean isAutoUpdateEnable();
    /**
     * Переключает состояние автоматического обновления.
     * @return true, если автоматическое обновление включено
     */
    public Boolean switchAutoUpdate();
}
