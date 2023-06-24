# Сохранение объектов с различными свойствами
![Version](https://img.shields.io/badge/version-1.0-blue.svg?cacheSeconds=2592000)

> Стек: 
> - Java 17
> - SpringBoot
> - PostgreSQL
> - SpringData



## Автор

👤 **Yuri Kaleganov**

* Github: [@Yuri_Kaleganov](https://github.com/Hebsat)
* hh.ru: [Калеганов Юрий](https://spb.hh.ru/resume/aa543f3dff06879f010039ed1f6f4948624661)

### Описание
Структура данных: <br>
![image](https://github.com/Hebsat/multiPropertyItems-store/assets/109655199/9931d6ef-d0a3-40e7-ba20-6bd58f7c16b6)
<br>
Реализована стратегия наследования "одна таблица для каждого класса с объединениями UNION" (InheritanceType.TABLE_PER_CLASS).
Объект Item может иметь произвольное количество свойств следующих типов: строка, целое число, число с плавающей точкой, boolean, а также любой другой объект,
у которого также могут быть перечисленные свойства, включая другие объекты любого уровня вложенности.<br>
Созданы 2 эндпойнта: 
- GET возвращает все объекты из БД
- POST принимает JSON и создает указанные объекты. Возращает корневой объект
### Пример
Создадим космический корабль Пегас из м/ф Тайна третьей планеты. Укажем краткое описание и зададим различные типы свойств:<br>
- строковые - название корабля (spaceshipName) и цвет (color)
- boolean - наличие экипажа (withCrew)
- целочисленые - объем топливного бака (fuelTankCapacity)
- с плавающей точкой - расход топлива (fuelСonsumption)
- а также в качестве свойства укажем команду (spaceShipCrew) из трех человек
Команда является самостоятельным объектом. Параметрами этого объекта указаны члены экипажа. Алисе отдадим в качестве питомца птицу говоруна. Выглядеть JSON будет следующим образом:
```
{
    "name" : "spaceship",
    "description" : "космический корабль с экспедицией в составе капитана Зеленого, профессора Селезнева и Алисы",
    "properties" : 
    [
        {
            "name" : "spaceshipName",
            "value" : "Пегас"
        },
        {
            "name" : "color",
            "value" : "metallic"
        },
        {
            "name" : "withCrew",
            "value" : true
        },
        {
            "name" : "fuelTankCapacity",
            "value" : 250
        },
        {
            "name" : "fuelСonsumption",
            "value" : 2.55
        },
        {
            "name" : "spaceShipCrew",
            "value" : 
            {
                "name" : "spaceShipCrew",
                "description" : "команда космического корабля",
                "properties" : 
                [
                    {
                        "name" : "capitan",
                        "value" : 
                        {
                            "name" : "human",
                            "description" : "капитан космического корабля",
                            "properties": 
                            [
                                {
                                    "name" : "name",
                                    "value" : "Зеленый"
                                }
                            ]
                        }
                    },
                    {
                        "name" : "scientist",
                        "value" : 
                        {
                            "name" : "human",
                            "description" : "старший научный сотрудник",
                            "properties": 
                            [
                                {
                                    "name" : "name",
                                    "value" : "Селезнев"
                                }
                            ]
                        }
                    },
                    {
                        "name" : "passenger",
                        "value" : 
                        {
                            "name" : "human",
                            "description" : "дочь профессора Селезнева",
                            "properties" :
                            [
                                {
                                    "name" : "name",
                                    "value" : "Алиса"
                                },
                                {
                                    "name" : "pet",
                                    "value" : 
                                    {
                                        "name" : "Птица Говорун",
                                        "description" : "птица Говорун отличается умом и сообразительностью",
                                        "properties" :
                                        [
                                            {
                                                "name" : "color",
                                                "value" : "белый"
                                            },
                                            {
                                                "name" : "wieght",
                                                "value" : 12.22
                                            }
                                        ]
                                    }
                                }
                            ]
                        }
                    }
                ]
            }
        }
    ]
}
```
После отправки запроса с указанным JSON получим объект spaceship со всеми его параметрами и параметрами его параметров):<br>
![image](https://github.com/Hebsat/multiPropertyItems-store/assets/109655199/7d1d8d09-ebf6-4472-9faf-f3366b5e5992) <br>
![image](https://github.com/Hebsat/multiPropertyItems-store/assets/109655199/95fedc11-7a27-460e-acb1-3ebae39a5ae9) <br>
![image](https://github.com/Hebsat/multiPropertyItems-store/assets/109655199/11e74a3e-bde6-49c4-b2b9-8f1ae08518c5) <br>

При этом в БД будет создано 6 объектов: космический корабль, команда, 3 члена экипажа и птица ![image](https://github.com/Hebsat/multiPropertyItems-store/assets/109655199/293c0eed-d273-4559-9cc6-c7ee15936c4b)

### Запуск
PostgreSQL: Создать новую схему. В application.properties указать путь к созданной схеме, задать имя и пароль.
