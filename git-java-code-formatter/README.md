# git-java-code-formatter

```
cd $HOME
git clone https://github.com/caofangkun/tools/git-java-code-formatter/  
vim ~/.gitconfig 
```
add following two lines:

```
[alias]
eclipse-formatter = !~/git-java-code-foramtter/run-eclipse-formatter
```
and you can now use following command to format java code :

```
git eclipse-formatter
```
