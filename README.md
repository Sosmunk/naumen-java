# naumen-java

## Домашнее задание по теме «Git для профессионала»

## Задание 1.
Проверено на занятии, ссылка на репозиторий с итоговым результатом:
https://github.com/Sosmunk/git-naumen

**Ответ на вопрос: "какие операции нужно совершить, если не использовать Pull Request (полный
набор команд от начала и до конца)?"**
- git checkout main
- git pull origin main
- git merge task1-mesilov
- git push origin main

## Задание 2.

1. Каждому создать новую ветку, например «task2-petrov»
2. Каждому закоммитить (commit) и запушить (push) любой файл
![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/9d7c033c-3715-44cb-b325-00655df22656)
3. Переключиться (checkout) на ветку main (или master)
![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/4e5c40a7-a4b0-4a8d-b9b9-bdbc01481740)

4. Закоммитить (commit) и запушить (push) любое изменение
![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/7447c3c8-14ba-46ae-9edd-1c69b281955c)
5. Переключиться (checkout) на свою ветку, например «task2-petrov»
6. Выполнить rebase от ветки main (или master)
![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/098fb4ed-5e47-4c94-a26a-73fd382e6b2d)
![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/ab5c8187-f704-4e44-a79b-de5d2441e276)
7. Запушить (push) новое состояние ветки «task2-petrov» (не получится, появится сообщение 
об ошибке «Push of the current branch "task2-petrov" was rejected. Remote changes need to 
be merged before pushing» или в консоли «error: failed to push some refs»)
![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/c17ddf9a-df5e-4635-89c5-ddd76626c81c)

8. **Объяснить почему не удалось запушить ветку**<br/>
В ветке origin/main появились изменения, которых еще нет в локальном main. Сама task2-mesilov ответвляется от локального main, в которой еще не было изменений из remote main.
Git не позволяет запушить изменения пока существует разница между удаленным и локальным main, от которого происходит rebase.
9. **Найти способы запушить ветку без лишних изменений, т.е. не должно появиться ни одного 
лишнего коммита (подсказка: есть два способа)**<br/>
Можно выполнить force push или удалить ветку.<br/>
Вариант с force push
 ![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/96b804ca-a36d-4959-b76e-362ffafca74c)
 ![alt text](https://github.com/Sosmunk/naumen-java/assets/104257106/75be3243-cf83-4380-8e43-78629a115764)

## Задание 3.

1. Создать новую ветку (branch), например «task3-petrov»
2. Создать пустой текстовый файл
3. Создать коммиты C1, C2, C3
- Коммит C1 содержит первую строчку "Строчка 1"
- Коммит C2 содержит вторую строчку "Строчка 2" 
- Коммит C3 содержит третью строчку "Строчка 3" 
4. Состояние репозитория: ![](https://github.com/Sosmunk/naumen-java/assets/104257106/cb1e5e88-d289-4e04-8b22-05ba75ed7b7c)
5. С помощью команды squash (merge --squash) объединить коммиты в один (C123)
![](https://github.com/Sosmunk/naumen-java/assets/104257106/652eaaa7-053e-4252-8c34-b46ee9338e79)

6. Ожидаемое состояние репозитория: в вашей ветке только один коммит C123, других 
коммитов нет
![](https://github.com/Sosmunk/naumen-java/assets/104257106/f27c69f2-878b-44de-9454-af2e0afaf728)
8. Доп. задание: выполнить это задание используя операцию Rebase Interactive (rebase -i), 
вместо операции squash (в исходное состояние можно вернуться с помощью drop commit
или операции reset
![](https://github.com/Sosmunk/naumen-java/assets/104257106/ca4b4f66-d2ec-4bcc-b398-7e757d67cf87)
![](https://github.com/Sosmunk/naumen-java/assets/104257106/9b365aaf-f5e8-4d8e-9010-2f07bb9ab8fe)



