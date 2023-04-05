package wildcard;

public class WildCard_JongManBook {

// Perfect Match
bool match(const string& w, const string& s)
{
	int pos = 0;
	while (pos < s.size() && pos < w.size() && (w[pos] == '?' || w[pos] == s[pos]))
		++pos;
	//더 이상 대응할 수 없으면 왜 while문이 끝났는지 확인한다.
	//2. 패턴 끝에 도달해서 끝난 경우 : 문자열도 끝났어야 대응됨
	if (pos == w.size())
		return pos == s.size();
	//4. *를 만나서 끝난 경우 : *에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
	if (w[pos] == '*')
		for (int skip = 0; pos + skip <= s.size(); skip++)
			if (match(w.substr(pos + 1), s.substr(pos + skip)))
				return true;
	//이 외의 경우에는 모두 대응되지 않는다
	return false;
}

// Using Memoization
int cache[101][101];
string W, S;
bool matchMemoized(int w, int s)
{
	int& ret = cache[w][s];
	if (ret != -1)	return ret;
	//W[w]와 S[s]를 맞춰나간다
	while (s < S.size() && w < W.size() && (W[w] == '?' || W[w] == S[s]))
	{
		++w;
		++s;
	}
	//더 이상 대응할수 없으면 왜 while문이 끝났는지 확인한다.
	//2. 패턴 끝에 도달해서 끝난 경우 : 문자열도 끝났어야 참
	if (w == W.size())
		return ret = (s == S.size());

	//4. *를 만나서 끝난 경우 : *에 몇글자를 대응해야 할지 재귀 호출하면서 확인
	if (W[w] == '*')
		for (int skip = 0; skip + s <= S.size(); ++skip)
			if (matchMemoized(w + 1, s + skip))
				return ret = 1;

	//3. 이외의 경우에는 모두 대응되지 않는다.
	return false;
}

// ---------------------------------------------------------------------------------------------------------//

// Code
int cache[101][101];
string W, S;

bool matchMemoized(int w, int s)
{
	int& ret = cache[w][s];
	if (ret != -1)	return ret;
	//W[w]와 S[s]를 맞춰나간다
	if (s < S.size() && w < W.size() && (W[w] == '?' || W[w] == S[s]))
	{
		return ret = matchMemoized(w + 1, s + 1);
	}
	//더 이상 대응할수 없으면 왜 while문이 끝났는지 확인한다.
	//2. 패턴 끝에 도달해서 끝난 경우 : 문자열도 끝났어야 참
	if (w == W.size())
		return ret = (s == S.size());

	//4. *를 만나서 끝난 경우 : *에 몇글자를 대응해야 할지 재귀 호출하면서 확인
	if (W[w] == '*')
		if (matchMemoized(w + 1, s ) || (s < S.size() && matchMemoized(w, s+1)))
			return ret = 1;

	//3. 이외의 경우에는 모두 대응되지 않는다.
	return false;
}

void cacheInit(int n)
{
	for (int i = 0; i <= n; i++)
		for (int j = 0; j <= n; j++)
			cache[i][j] = -1;
}

void solve()
{
	int c;
	cin >> c;
	for (int i = 0; i < c; i++)
	{
		vector<string> sucessCards;
		cin >> W;

		int n;
		cin >> n;
		for (int j = 0; j < n; j++)
		{
			//cache init
			cacheInit(100);
			cin >> S;
			//solve ㄱㄱ
			if (matchMemoized(0, 0) == true)
				sucessCards.push_back(S);
		}

		sort(begin(sucessCards), end(sucessCards));
		for (auto& ele : sucessCards)
			cout << ele << endl;
	}
}

int main()
{
	solve();
	return 0;

}
