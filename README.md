# Chess moves finder
This service takes a figure type (king, rook, bishop, knight) and cell (a1-h8) and then returns a list of cells that the figure can move to from the selected one.

## How to use
To get a list of moves you should run the application and go to
```
http://127.0.0.1:8080/<FIGURE>?cell=<CELL>
```
- Instead of `<FIGURE>` enter the desired figure: `king`, `rook`, `bishop`, `knight`
- Instead of `<CELL>` enter the desired cell (a1-h8): `a1`, `a2`, ..., `h7`, `h8`

If you enter incorrect cell then you will get a corresponding message. 
## Example
```
http://127.0.0.1:8080/knight?cell=c4
```
Return:
```
Possible moves:
a5
a3
b6
b2
d6
d2
e5
e3
```
## How to run with docker
Build docker image:
```
$ docker build -t ChessMovesFinder
```
Run docker image:
```
$ docker run -p 8080:8080 ChessMovesFinder
```
## Lisense
[Apache](./LICENSE)