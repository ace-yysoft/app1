<template>
  <div class="container">
    <header class="header">
      <h1>GS1 디지털링크 관리 시스템</h1>
      <p class="subtitle">Digital Link Management System</p>
    </header>

    <div class="card">
      <h2>새 디지털링크 등록</h2>
      <div class="link-form">
        <div class="form-group">
          <label>GTIN (상품식별코드)</label>
          <input v-model="newLink.gtin" placeholder="예: 8801234567890" class="form-input">
        </div>
        <div class="form-group">
          <label>LOT (로트번호)</label>
          <input v-model="newLink.lot" placeholder="예: ABC123" class="form-input">
        </div>
        <div class="form-group">
          <label>EXP (유효기간)</label>
          <input v-model="newLink.exp" type="date" class="form-input">
        </div>
        <div class="form-group">
          <label>Target URL</label>
          <input v-model="newLink.targetUrl" placeholder="https://yysoft.kr/product/" class="form-input">
        </div>
        <button @click="createLink" class="btn-primary">디지털링크 생성</button>
      </div>
    </div>

    <div class="card">
      <h2>등록된 디지털링크 목록</h2>
      <div class="link-list">
        <table>
          <thead>
            <tr>
              <th>GTIN</th>
              <th>LOT</th>
              <th>유효기간</th>
              <th>Target URL</th>
              <th>생성일시</th>
              <th>관리</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="link in links" :key="link.id">
              <td>{{ link.gtin }}</td>
              <td>{{ link.lot }}</td>
              <td>{{ formatDate(link.exp) }}</td>
              <td>
                <a :href="link.targetUrl" target="_blank">{{ link.targetUrl }}</a>
              </td>
              <td>{{ formatDateTime(link.createdAt) }}</td>
              <td>
                <button class="btn-small" @click="copyUrl(link.targetUrl)">URL 복사</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'App',
  data() {
    return {
      newLink: {
        gtin: '',
        lot: '',
        exp: '',
        targetUrl: 'https://yysoft.kr/product/'
      },
      links: []
    }
  },
  methods: {
    async createLink() {
      try {
        const response = await axios.post('http://localhost:8080/api/gs1', this.newLink)
        this.links.unshift(response.data)
        this.resetForm()
        alert('디지털링크가 생성되었습니다.')
      } catch (error) {
        console.error('Error:', error)
        alert('오류가 발생했습니다.')
      }
    },
    async loadLinks() {
      try {
        const response = await axios.get('http://localhost:8080/api/gs1')
        this.links = response.data
      } catch (error) {
        console.error('Error:', error)
      }
    },
    resetForm() {
      this.newLink = {
        gtin: '',
        lot: '',
        exp: '',
        targetUrl: 'https://yysoft.kr/product/'
      }
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString()
    },
    formatDateTime(datetime) {
      return new Date(datetime).toLocaleString()
    },
    copyUrl(url) {
      navigator.clipboard.writeText(url)
      alert('URL이 클립보드에 복사되었습니다.')
    }
  },
  mounted() {
    this.loadLinks()
  }
}
</script>

<style>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.header {
  text-align: center;
  margin-bottom: 40px;
  color: #2c3e50;
}

.subtitle {
  color: #7f8c8d;
  margin-top: -10px;
}

.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 20px;
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #34495e;
  font-weight: bold;
}

.form-input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.btn-primary {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  width: 100%;
}

.btn-primary:hover {
  background-color: #2980b9;
}

.btn-small {
  background-color: #95a5a6;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 3px;
  cursor: pointer;
  font-size: 12px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f8f9fa;
  color: #2c3e50;
  font-weight: bold;
}

tr:hover {
  background-color: #f5f6fa;
}

a {
  color: #3498db;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
